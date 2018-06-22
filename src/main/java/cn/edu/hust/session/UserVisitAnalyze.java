package cn.edu.hust.session;

import cn.edu.hust.conf.ConfigurationManager;
import cn.edu.hust.constant.Constants;
import cn.edu.hust.dao.TaskDao;
import cn.edu.hust.dao.factory.DaoFactory;
import cn.edu.hust.domain.Task;
import cn.edu.hust.mockData.MockData;
import cn.edu.hust.util.DateUtils;
import cn.edu.hust.util.ParamUtils;
import cn.edu.hust.util.StringUtils;
import cn.edu.hust.util.ValidUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.spark.Accumulator;
import org.apache.spark.AccumulatorParam;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.hive.HiveContext;
import org.joda.time.DateTime;
import scala.Tuple2;

import java.util.Date;

/**
 * 用户可以查询的范围包含
 * 1。用户的职业
 * 2。用户的性别
 * 3。用户城市
 * 4。用户年龄
 * 5。获取搜索词
 * 6。获取点击品类
 */
public class UserVisitAnalyze {
    public static void main(String[] args)
    {
        /**
         * 构建spark上下文
         */
        SparkConf conf=new SparkConf().setAppName(Constants.APP_NAME_SESSION).setMaster("local[3]");
        JavaSparkContext context=new JavaSparkContext(conf);
        SQLContext sc=getSQLContext(context.sc());
        //生成模拟数据
        mock(context,sc);

        //拿到相应的Dao组建
        TaskDao dao= DaoFactory.getTaskDao();
        //从外部传入的参数获取任务的id
        Long taskId=ParamUtils.getTaskIdFromArgs(args);
        //从数据库中查询出相应的task
        Task task=dao.findTaskById(taskId);
        JSONObject jsonObject=JSONObject.parseObject(task.getTaskParam());

        //获取指定范围内的Sesssion
        JavaRDD<Row> sessionRangeDate=getActionRDD(sc,jsonObject);
        //按照Sesson进行聚合
        JavaPairRDD<String,String> sesssionAggregateInfoRDD=aggregateBySessionId(sc,sessionRangeDate);

        //通过条件对RDD进行筛选
        // 重构，同时统计
        Accumulator<String> sessionAggrStatAccumulator=context.accumulator("",new SessionAggrStatAccumulator());

        JavaPairRDD<String,String> filteredSessionRDD=filterSessionAndAggrStat(sesssionAggregateInfoRDD,jsonObject,sessionAggrStatAccumulator);

        //session聚合统计，统计出访问时长和访问步长的各个区间所占的比例
        /**
         * 重构实现的思路：
         * 1。不要去生成任何的新RDD
         * 2。不要去单独遍历一遍sesion的数据
         * 3。可以在聚合数据的时候可以直接计算session的访问时长和访问步长
         * 4。在以前的聚合操作中，可以在以前的基础上进行计算加上自己实现的Accumulator来进行一次性解决
         * 开发Spark的经验准则
         * 1。尽量少生成RDD
         * 2。尽量少对RDD进行蒜子操作，如果可能，尽量在一个算子里面，实现多个需求功能
         * 3。尽量少对RDD进行shuffle算子操作，比如groupBykey、reduceBykey、sortByKey
         *         shuffle操作，会导致大量的磁盘读写，严重降低性能
         *         有shuffle的算子，和没有shuffle的算子，甚至性能相差极大
         *         有shuffle的算子，很容易造成性能倾斜，一旦数据倾斜，简直就是性能杀手
         * 4。无论做什么功能，性能第一
         *         在大数据项目中，性能最重要。主要是大数据以及大数据项目的特点，决定了大数据的程序和项目速度，都比较满
         *         如果不考虑性能的话，就会导致一个大数据处理程序运行长达数个小时，甚至是数个小时，对用户的体验，简直是
         *         一场灾难。
         */

        //计算各个session占比,并写入MySQL
        calculateAndPersist(sessionAggrStatAccumulator.value(),taskId);
        //关闭spark上下文
        context.close();
    }




    /**
     * 用于判断是否是生产环境
     * @param sc
     * @return
     */
    public static SQLContext getSQLContext(SparkContext sc)
    {
        boolean local= ConfigurationManager.getBoolean(Constants.SPARK_LOCAL);
        if(local)
        {
            return new SQLContext(sc);
        }
        return new HiveContext(sc);
    }

    private static void mock(JavaSparkContext context,SQLContext sc)
    {
        boolean local= ConfigurationManager.getBoolean(Constants.SPARK_LOCAL);
        if(local)
        {
            MockData.mock(context,sc);
        }

    }

    /**
     * 获取指定日期范围内的数据
     * @param sc
     * @param taskParam
     * @return
     */
    private static JavaRDD<Row> getActionRDD(SQLContext sc, JSONObject taskParam)
    {
        String startTime=ParamUtils.getParam(taskParam,Constants.PARAM_STARTTIME);
        String endTime=ParamUtils.getParam(taskParam,Constants.PARAM_ENDTIME);
        String sql="select *from user_visit_action where date>='"+startTime+"' and date<='"+endTime+"'";
        DataFrame df=sc.sql(sql);
        return df.javaRDD();
    }

    private static JavaPairRDD<String,String> aggregateBySessionId(SQLContext sc, JavaRDD<Row> sessionRangeDate) {
        /**
         * 先将数据映射成map格式
         */
        JavaPairRDD<String,Row> sessionActionPair=sessionRangeDate.mapToPair(new PairFunction<Row, String,Row>() {
            @Override
            public Tuple2<String, Row> call(Row row) throws Exception {
                return new Tuple2<String, Row>(row.getString(2),row);
            }
        });
        /**
         * 根据sessionId进行分组
         */
        JavaPairRDD<String,Iterable<Row>> sessionActionGrouped=sessionActionPair.groupByKey();

        JavaPairRDD<Long,String> sessionPartInfo=sessionActionGrouped.mapToPair(new PairFunction<Tuple2<String, Iterable<Row>>, Long, String>() {
            @Override
            public Tuple2<Long, String> call(Tuple2<String, Iterable<Row>> stringIterableTuple2) throws Exception {
                String sessionId=stringIterableTuple2._1;
                Iterable<Row> rows=stringIterableTuple2._2;
                StringBuffer searchKeywords=new StringBuffer();
                StringBuffer clickCategoryIds=new StringBuffer();
                Long userId=null;
                Date startTime=null;
                Date endTime=null;
                int stepLength=0;
                for (Row row:rows)
                {
                    if(userId==null)
                        userId=row.getLong(1);
                    String searchKeyword=row.getString(5);
                    Long clickCategoryId=row.getLong(6);
                    //判断是否需要拼接
                    if(StringUtils.isNotEmpty(searchKeyword))
                    {
                        if(!searchKeywords.toString().contains(searchKeyword))
                            searchKeywords.append(searchKeyword+",");
                    }

                    if(clickCategoryId!=null)
                    {
                        if(!clickCategoryId.toString().contains(String.valueOf(clickCategoryId)))
                            clickCategoryIds.append(String.valueOf(clickCategoryId)+",");
                    }

                    //计算session开始时间和结束时间
                    Date actionTime= DateUtils.parseTime(row.getString(4));
                    if(startTime==null)
                        startTime=actionTime;
                    if(endTime==null)
                        endTime=null;
                    if(actionTime.before(startTime))
                    {
                        startTime=actionTime;
                    }
                    if(actionTime.after(endTime))
                    {
                        endTime=actionTime;
                    }
                        stepLength++;
                }
                //访问时长(s)
                Long visitLengtth=(endTime.getTime()-startTime.getTime())/1000;

                String searchKeywordsInfo=StringUtils.trimComma(searchKeywords.toString());
                String clickCategoryIdsInfo=StringUtils.trimComma(clickCategoryIds.toString());
                String info=Constants.FIELD_SESSIONID+"="+sessionId+"|"+Constants.FIELD_SERACH_KEYWORDS+"="+searchKeywordsInfo+"|"
                        +Constants.FIELD_CLICK_CATEGORYIDS+"="+clickCategoryIdsInfo+Constants.FIELD_VISIT_LENGTH+"="+visitLengtth+"|"
                        +Constants.FIELD_STEP_LENGTH+"="+stepLength;
                return new Tuple2<Long, String>(userId,info);
            }
        });

        //查询所有的用户数据
         String sql="select * from user_info";
         JavaRDD<Row> userInfoRDD=sc.sql(sql).javaRDD();
         //将用户信息映射成map
         JavaPairRDD<Long,Row> userInfoPariRDD=userInfoRDD.mapToPair(new PairFunction<Row, Long, Row>() {
             @Override
             public Tuple2<Long, Row> call(Row row) throws Exception {
                 return new Tuple2<Long, Row>(row.getLong(0),row);
             }
         });
         //将两个信息join在一起
        JavaPairRDD<Long,Tuple2<String,Row>> tuple2JavaPairRDD=sessionPartInfo.join(userInfoPariRDD);

        /**
         * 拿到所需的session
         */
        JavaPairRDD<String,String> sessionInfo=tuple2JavaPairRDD.mapToPair(new PairFunction<Tuple2<Long,Tuple2<String,Row>>, String, String>() {
            @Override
            public Tuple2<String, String> call(Tuple2<Long, Tuple2<String, Row>> longTuple2Tuple2) throws Exception {
                String sessionPartInfo=longTuple2Tuple2._2._1;
                Row userInfo=longTuple2Tuple2._2._2;
                //拿到需要的用户信息
                int age=userInfo.getInt(3);
                String professional=userInfo.getString(4);
                String city=userInfo.getString(5);
                String sex=userInfo.getString(6);
                //拼接字符串
                String fullInfo=sessionPartInfo+"|"+Constants.FIELD_AGE+"="+age+"|"
                        +Constants.FIELD_PROFESSIONAL+"="+professional+"|"+Constants.FIELD_CITY+"="+city+"|"+Constants.FIELD_SEX+"="+sex;
                String session=StringUtils.getFieldFromConcatString(sessionPartInfo,"\\|",Constants.FIELD_SESSIONID);
                return new Tuple2<String, String>(session,fullInfo);
            }
        });

        return sessionInfo;
    }


    /**
     * 根据条件进行session的筛选
     * @param sessionInfoRDD
     * @param taskParam
     * @param sessionAggrStatAccumulator
     * @return
     */
    private static JavaPairRDD<String,String> filterSessionAndAggrStat(JavaPairRDD<String, String> sessionInfoRDD, final JSONObject taskParam, final Accumulator<String> sessionAggrStatAccumulator){
        //得到条件
        String startAge=ParamUtils.getParam(taskParam,Constants.PARAM_STARTAGE);
        String endAge=ParamUtils.getParam(taskParam,Constants.PARAM_ENDAGE);
        String professionals=ParamUtils.getParam(taskParam,Constants.PARAM_PROFESSONALS);
        String cities=ParamUtils.getParam(taskParam,Constants.PARAM_CIYTIES);
        String sex= ParamUtils.getParam(taskParam,Constants.PARAM_SEX);
        String keyWords=ParamUtils.getParam(taskParam,Constants.PARAM_SERACH_KEYWORDS);
        String categoryIds=ParamUtils.getParam(taskParam,Constants.PARAM_CLICK_CATEGORYIDS);

        //拼接时间
        String _paramter=(startAge!=null?Constants.PARAM_STARTAGE+"="+startAge+"|":"")+
                (endAge!=null?Constants.PARAM_ENDAGE+"="+endAge+"|":"")+(professionals!=null?Constants.PARAM_PROFESSONALS+"="+professionals+"|":"")+
                (cities!=null?Constants.PARAM_CIYTIES+"="+cities+"|":"")+(sex!=null?Constants.PARAM_SEX+"="+sex+"|":"")+
                (keyWords!=null?Constants.PARAM_SERACH_KEYWORDS+"="+keyWords+"|":"")+(categoryIds!=null?Constants.PARAM_CLICK_CATEGORYIDS+"="+categoryIds+"|":"");


        if(_paramter.endsWith("\\|"))
            _paramter=_paramter.substring(0,_paramter.length()-1);

        final String paramter=_paramter;

        JavaPairRDD<String,String> filteredSessionRDD=sessionInfoRDD.filter(new Function<Tuple2<String, String>, Boolean>() {
            @Override
            public Boolean call(Tuple2<String, String> tuple2) throws Exception {
                String sessionInfo=tuple2._2;
                //按照条件进行过滤
                //按照年龄进行过滤
                if(!ValidUtils.between(sessionInfo,Constants.FIELD_AGE,paramter,Constants.PARAM_STARTAGE,Constants.PARAM_ENDAGE))
                    return  false;
                //按照职业进行过滤
                if(!ValidUtils.in(sessionInfo,Constants.FIELD_PROFESSIONAL,paramter,Constants.PARAM_PROFESSONALS))
                    return false;
                //按照城市进行过滤
                if(!ValidUtils.in(sessionInfo,Constants.FIELD_CITY,paramter,Constants.PARAM_CIYTIES))
                    return false;
                //按照性别进行筛选
                if(!ValidUtils.equal(sessionInfo,Constants.FIELD_SEX,paramter,Constants.PARAM_SEX))
                    return false;
                //按照搜索词进行过滤，只要有一个搜索词即可
                if(!ValidUtils.in(sessionInfo,Constants.FIELD_SERACH_KEYWORDS,paramter,Constants.PARAM_PROFESSONALS))
                    return false;
                if(!ValidUtils.in(sessionInfo,Constants.FIELD_CLICK_CATEGORYIDS,paramter,Constants.FIELD_CLICK_CATEGORYIDS))
                    return false;
                //如果经过了之前的所有的过滤条件，也就是满足用户筛选条件
                sessionAggrStatAccumulator.add(Constants.SESSION_COUNT);
                //计算出访问时长和访问步长的范围并进行相应的累加
                Long visitLength=Long.valueOf(StringUtils.getFieldFromConcatString(sessionInfo,"\\|",Constants.FIELD_VISIT_LENGTH));
                Long stepLength=Long.valueOf(StringUtils.getFieldFromConcatString(sessionInfo,"\\|",Constants.FIELD_STEP_LENGTH));
                //使用函数进行统计
                calculateVisitLength(visitLength);
                calculateStepLength(stepLength);
                return true;
            }

            //统计访问时长的数量
            private void calculateVisitLength(Long visitLegth)
            {
                if(visitLegth>=1&&visitLegth<=3)
                    sessionAggrStatAccumulator.add(Constants.TIME_PERIOD_1s_3s);
                else if(visitLegth>=4&&visitLegth<=6)
                    sessionAggrStatAccumulator.add(Constants.TIME_PERIOD_4s_6s);
                else if(visitLegth>=7&&visitLegth<=9)
                    sessionAggrStatAccumulator.add(Constants.TIME_PERIOD_7s_9s);
                else if(visitLegth>=10&&visitLegth<=30)
                    sessionAggrStatAccumulator.add(Constants.TIME_PERIOD_10s_30s);
                else if(visitLegth>30&&visitLegth<=60)
                    sessionAggrStatAccumulator.add(Constants.TIME_PERIOD_30s_60s);
                else if(visitLegth>60&&visitLegth<=180)
                    sessionAggrStatAccumulator.add(Constants.TIME_PERIOD_1m_3m);
                else if(visitLegth>180&&visitLegth<=600)
                    sessionAggrStatAccumulator.add(Constants.TIME_PERIOD_3m_10m);
                else if(visitLegth>600&&visitLegth<=1800)
                    sessionAggrStatAccumulator.add(Constants.TIME_PERIOD_10m_30m);
                else if(visitLegth>1800)
                    sessionAggrStatAccumulator.add(Constants.TIME_PERIOD_30m);
            }
            //统计访问步长的数量
            private void calculateStepLength(Long stepLength)
            {
                if(stepLength>=1&&stepLength<=3)
                    sessionAggrStatAccumulator.add(Constants.STEP_PERIOD_1_3);
                else if(stepLength>=4&&stepLength<=6)
                    sessionAggrStatAccumulator.add(Constants.STEP_PERIOD_4_6);
                else if(stepLength>=7&&stepLength<=9)
                    sessionAggrStatAccumulator.add(Constants.STEP_PERIOD_7_9);
                else if(stepLength>=10&&stepLength<=30)
                    sessionAggrStatAccumulator.add(Constants.STEP_PERIOD_10_30);
                else if(stepLength>30&&stepLength<=60)
                    sessionAggrStatAccumulator.add(Constants.STEP_PERIOD_30_60);
                else if(stepLength>60)
                sessionAggrStatAccumulator.add(Constants.STEP_PERIOD_60);
            }
        });
        return filteredSessionRDD;
    }


    private static void calculateAndPersist(String value,Long taskId) {
    }
}
