package cn.edu.hust.dao.impl;

import cn.edu.hust.dao.Top10CategorySessionDao;
import cn.edu.hust.domain.Top10CategorySession;
import cn.edu.hust.jdbc.JDBCHelper;

import java.util.ArrayList;
import java.util.List;

public class Top10CategorySessionDaoImpl implements Top10CategorySessionDao
{
    @Override
    public void batchInsert(List<Top10CategorySession> top10CategorySessionList) {
        String sql="insert into top10_category_session values(?,?,?,?)";
        List<Object[]> paramList=new ArrayList<Object[]>();
        for(Top10CategorySession top10CategorySession:top10CategorySessionList)
        {
            Object[] param=new Object[]{top10CategorySession.getTaskId(),top10CategorySession.getCategoryId(),
            top10CategorySession.getSessionId(),top10CategorySession.getClickCount()};
            paramList.add(param);
        }
        JDBCHelper.getInstance().excuteBatch(sql,paramList);
    }
}
