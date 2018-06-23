package cn.edu.hust.dao.impl;

import cn.edu.hust.dao.SessionRandomExtractDao;
import cn.edu.hust.domain.SessionRandomExtract;
import cn.edu.hust.jdbc.JDBCHelper;

import java.util.ArrayList;
import java.util.List;

public class SessionRandomExtractDaoImpl implements SessionRandomExtractDao{
    @Override
    public void batchInsert(List<SessionRandomExtract> sessionRandomExtractList) {
        String sql="insert into session_random_extract values(?,?,?,?,?)";
        List<Object[]> paramList=new ArrayList<Object[]>();
        for (SessionRandomExtract sessionRandomExtract:sessionRandomExtractList) {
            Object[] params=new Object[]{sessionRandomExtract.getTaskId(),sessionRandomExtract.getSessionId()
            ,sessionRandomExtract.getStartTime(),sessionRandomExtract.getSearchKeyWords(),sessionRandomExtract.getClick_category_ids()};
            paramList.add(params);
        }
        JDBCHelper.getInstance().excuteBatch(sql,paramList);
    }
}
