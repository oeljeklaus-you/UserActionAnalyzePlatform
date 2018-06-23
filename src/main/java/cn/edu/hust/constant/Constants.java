package cn.edu.hust.constant;

public class Constants {
    /**
     * 项目配置常量
     */
    public static final String JDBC_DRIVER="jdbc.driver";
    public static final String JDBC_URL="jdbc.url";
    public static final String JDBC_USERNAME="jdbc.username";
    public static final String JDBC_PSSWORD="jdbc.password";
    public static final String JDBC_ACTIVE="jdbc.active";

    /**
     * Spark作业相关常量
     */
    public static final String APP_NAME_SESSION="UserVisitAnalyze";
    public static final String SPARK_LOCAL="spark_local";
    public static final String FIELD_SESSIONID="sessionId";
    public static final String FIELD_SERACH_KEYWORDS="searchKeywords";
    public static final String FIELD_CLICK_CATEGORYIDS="clickCategoryIds";
    public static final String FIELD_AGE="age";
    public static final String FIELD_CITY="city";
    public static final String FIELD_SEX="sex";
    public static final String FIELD_PROFESSIONAL="professional";
    public static final String FIELD_VISIT_LENGTH="visitLength";
    public static final String FIELD_STEP_LENGTH="stepLength";
    public static final String FIELD_START_TIME="startTime";
    public static final String FIELD_CATEGORY_ID="categoryId";
    public static final String FIELD_CLICK_CATEGORY="categoryId";
    public static final String FIELD_ORDER_CATEGORY="clickCategory";
    public static final String FIELD_PAY_CATEGORY="orderCategory";

    /**
     * Spark任务相关厂常量
     */
    public static final String PARAM_STARTTIME ="startDate";
    public static final String PARAM_ENDTIME ="endDate";
    public static final String PARAM_STARTAGE ="startAge";
    public static final String PARAM_ENDAGE ="endAge";
    public static final String PARAM_PROFESSONALS ="professionals";
    public static final String PARAM_CIYTIES ="cities";
    public static final String PARAM_SEX ="sex";
    public static final String PARAM_SERACH_KEYWORDS="searchKeywords";
    public static final String PARAM_CLICK_CATEGORYIDS="clickCategoryIds";

    public static final String SESSION_COUNT = "session_count";
    public static final String TIME_PERIOD_1s_3s = "1s_3s";
    public static final String TIME_PERIOD_4s_6s = "4s_6s";
    public static final String TIME_PERIOD_7s_9s = "7s_9s";
    public static final String TIME_PERIOD_10s_30s = "10s_30s";
    public static final String TIME_PERIOD_30s_60s = "30s_60s";
    public static final String TIME_PERIOD_1m_3m = "1m_3m";
    public static final String TIME_PERIOD_3m_10m = "3m_10m";
    public static final String TIME_PERIOD_10m_30m = "10m_30m";
    public static final String TIME_PERIOD_30m = "30m";

    public static final String STEP_PERIOD_1_3 = "1_3";
    public static final String STEP_PERIOD_4_6 = "4_6";
    public static final String STEP_PERIOD_7_9 = "7_9";
    public static final String STEP_PERIOD_10_30 = "10_30";
    public static final String STEP_PERIOD_30_60 = "30_60";
    public static final String STEP_PERIOD_60 = "60";

}
