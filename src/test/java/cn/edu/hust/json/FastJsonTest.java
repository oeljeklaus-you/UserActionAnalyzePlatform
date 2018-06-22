package cn.edu.hust.json;

import com.alibaba.fastjson.JSONArray;
import org.junit.Test;

public class FastJsonTest {
    /**
     * Json测试
     */
    @Test
    public void test1()
    {
        String json="[{'name':'Tom','age':23},{'name':'LiLi','age':24}]";
        JSONArray array=JSONArray.parseArray(json);
        System.out.println(array.getJSONObject(0).get("name"));
    }
}
