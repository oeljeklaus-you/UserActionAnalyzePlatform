package cn.edu.hust.session;

import cn.edu.hust.constant.Constants;
import cn.edu.hust.util.StringUtils;
import org.apache.spark.AccumulatorParam;

public class SessionAggrStatAccumulator implements AccumulatorParam<String>{
    @Override
    public String addAccumulator(String s, String t1) {
        return add(s,t1);
    }


    @Override
    public String addInPlace(String s, String r1) {
        return add(s,r1);
    }

    //主要用于数据的初始化，这里主要返回一个值就是所有范围区间得的数量
    @Override
    public String zero(String s) {
        return Constants.SESSION_COUNT + "=0|"
                + Constants.TIME_PERIOD_1s_3s + "=0|"
                + Constants.TIME_PERIOD_4s_6s + "=0|"
                + Constants.TIME_PERIOD_7s_9s + "=0|"
                + Constants.TIME_PERIOD_10s_30s + "=0|"
                + Constants.TIME_PERIOD_30s_60s + "=0|"
                + Constants.TIME_PERIOD_1m_3m + "=0|"
                + Constants.TIME_PERIOD_3m_10m + "=0|"
                + Constants.TIME_PERIOD_10m_30m + "=0|"
                + Constants.TIME_PERIOD_30m + "=0|"
                + Constants.STEP_PERIOD_1_3 + "=0|"
                + Constants.STEP_PERIOD_4_6 + "=0|"
                + Constants.STEP_PERIOD_7_9 + "=0|"
                + Constants.STEP_PERIOD_10_30 + "=0|"
                + Constants.STEP_PERIOD_30_60 + "=0|"
                + Constants.STEP_PERIOD_60 + "=0";
    }

    private String add(String v1,String v2)
    {
        if(StringUtils.isEmpty(v1)) return v2;
        String value=StringUtils.getFieldFromConcatString(v1,"\\|",v2);
        if(value!=null)
        {
            int newValue=Integer.valueOf(value)+1;
           return StringUtils.setFieldInConcatString(v1,"\\|",v2,String.valueOf(newValue));
        }
        return v1;
    }
}
