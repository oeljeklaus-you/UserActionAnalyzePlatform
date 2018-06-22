package cn.edu.hust.utils;

import cn.edu.hust.util.ParamUtils;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class ParamUtilsTest {
    @Test
    public void test2()
    {
        String json="{\"startAge\":[\"10\"],\"endAge\":[\"34\"],\"startDate\":[\"2018-06-22\"],\"endDate\":[\"2018-06-22\"]}";
        JSONObject param=JSONObject.parseObject(json);
        //param.getJSONArray("startAge");
        System.out.println(ParamUtils.getParam(param,"startDate"));
    }
}
