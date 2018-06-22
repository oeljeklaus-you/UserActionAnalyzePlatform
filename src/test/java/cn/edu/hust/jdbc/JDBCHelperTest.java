package cn.edu.hust.jdbc;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCHelperTest {
    @Test
    public void testUpdate()
    {
        String sql="insert into user(username,age) values(?,?)";
        Object[] params={"zhangsan",12};
        JDBCHelper.getInstance().excuteUpdate(sql,params);
    }


    @Test
    public void testBatch()
    {
        String sql="insert into user(username,age) values(?,?)";
        List<Object[]> params=new ArrayList();
        params.add(new Object[]{"lisi",23});
        params.add(new Object[]{"wangwu",28});
        JDBCHelper.getInstance().excuteBatch(sql,params);
    }

    @Test
    public void testQuery()
    {
        String sql="select * from user where username=?";
        Object[] params={"zhangsan"};
        JDBCHelper.getInstance().excuteQuery(sql, params, new JDBCHelper.QueryCallBack() {
            @Override
            public void process(ResultSet rs) {
                try {
                    if(rs.next())
                    {
                        System.out.println("结果是"+rs.getInt(2));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
