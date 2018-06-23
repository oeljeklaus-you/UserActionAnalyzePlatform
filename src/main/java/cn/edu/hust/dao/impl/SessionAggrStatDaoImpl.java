package cn.edu.hust.dao.impl;

import cn.edu.hust.dao.SessionAggrStatDao;
import cn.edu.hust.domain.SessionAggrStat;
import cn.edu.hust.jdbc.JDBCHelper;

import java.util.ArrayList;
import java.util.List;

public class SessionAggrStatDaoImpl implements SessionAggrStatDao{
    @Override
    public void insert(SessionAggrStat sessionAggrStat) {
        String sql="insert into session_aggr_stat values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params={sessionAggrStat.getTaskId(),sessionAggrStat.getSessionCount(),sessionAggrStat.getVisit_Length_1s_3s(),
                sessionAggrStat.getVisit_Length_4s_6s(),sessionAggrStat.getVisit_Length_7s_9s(),
                sessionAggrStat.getVisit_Length_10s_30s(),sessionAggrStat.getVisit_Length_30s_60s(),
                sessionAggrStat.getVisit_Length_1m_3m(),sessionAggrStat.getVisit_Length_3m_10m()
                ,sessionAggrStat.getVisit_Length_10m_30m(),sessionAggrStat.getVisit_Length_30m(),
                sessionAggrStat.getStep_Length_1_3(),sessionAggrStat.getStep_Length_4_6(),sessionAggrStat.getStep_Length_7_9(),
                sessionAggrStat.getStep_Length_7_9(),sessionAggrStat.getStep_Length_10_30(),
                sessionAggrStat.getStep_Length_30_60()};
        JDBCHelper.getInstance().excuteUpdate(sql,params);
    }

    @Override
    public void batchInsert(List<SessionAggrStat> sessionAggrStatList) {
        String sql="insert into session_aggr_stat values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        List<Object[]> paramList=new ArrayList<Object[]>();
        for (SessionAggrStat sessionAggrStat:sessionAggrStatList)
        {
            Object[] params={sessionAggrStat.getTaskId(),sessionAggrStat.getSessionCount(),sessionAggrStat.getVisit_Length_1s_3s(),
                    sessionAggrStat.getVisit_Length_4s_6s(),sessionAggrStat.getVisit_Length_7s_9s(),
                    sessionAggrStat.getVisit_Length_10s_30s(),sessionAggrStat.getVisit_Length_30s_60s(),
                    sessionAggrStat.getVisit_Length_1m_3m(),sessionAggrStat.getVisit_Length_3m_10m()
                    ,sessionAggrStat.getVisit_Length_10m_30m(),sessionAggrStat.getVisit_Length_30m(),
                    sessionAggrStat.getStep_Length_1_3(),sessionAggrStat.getStep_Length_4_6(),sessionAggrStat.getStep_Length_7_9(),
                    sessionAggrStat.getStep_Length_7_9(),sessionAggrStat.getStep_Length_10_30(),
                    sessionAggrStat.getStep_Length_30_60()};
            paramList.add(params);
        }
        JDBCHelper.getInstance().excuteBatch(sql,paramList);
    }
}
