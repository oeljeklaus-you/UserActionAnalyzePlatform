package cn.edu.hust.dao.impl;

import cn.edu.hust.dao.TaskDao;
import cn.edu.hust.domain.Task;
import cn.edu.hust.jdbc.JDBCHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskDaoImpl implements TaskDao{
    @Override
    public Task findTaskById(Long id) {
        String sql="select * from task where task_id=?";
        final Task task=new Task();
        JDBCHelper.getInstance().excuteQuery(sql, new Object[]{id}, new JDBCHelper.QueryCallBack() {
            @Override
            public void process(ResultSet rs) {
                try {
                    if(rs.next())
                    {
                        Long id=rs.getLong(1);
                        String taskName=rs.getString(2);
                        String createTime=rs.getString(3);
                        String startTime=rs.getString(4);
                        String finishTime=rs.getString(5);
                        String taskType=rs.getString(6);
                        String taskStatus=rs.getString(7);
                        String taskParam=rs.getString(8);
                        task.set(id,taskName,createTime,startTime,finishTime,taskType,taskStatus,taskParam);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        return task;
    }
}
