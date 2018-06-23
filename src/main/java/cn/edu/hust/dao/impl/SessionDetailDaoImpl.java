package cn.edu.hust.dao.impl;

import cn.edu.hust.dao.SessionDetailDao;
import cn.edu.hust.domain.SessionDetail;
import cn.edu.hust.jdbc.JDBCHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SessionDetailDaoImpl implements SessionDetailDao{
    @Override
    public void insert(SessionDetail sessionDetail) {
        String sql="insert into session_detail values(?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] object=new  Object[]{sessionDetail.getTaskId(),sessionDetail.getUserId(),
        sessionDetail.getSessinId(),sessionDetail.getPageid(),sessionDetail.getActionTime(),
        sessionDetail.getSearchKeyWord(),sessionDetail.getClickCategoryId(),sessionDetail.getClickProductId()
        ,sessionDetail.getOrderCategoryIds(),sessionDetail.getOrderProductIds(),sessionDetail.getPayCategoryIds(),sessionDetail.getPayProductIds()};
        JDBCHelper.getInstance().excuteUpdate(sql,object);
    }

    @Override
    public void batchInsert(List<SessionDetail> sessionDetailList) {
        String sql="insert into session_detail values(?,?,?,?,?,?,?,?,?,?,?,?)";
        List<Object[]> paramList=new ArrayList<Object[]>();
        for (SessionDetail sessionDetail:sessionDetailList)
        {
            Object[] object=new  Object[]{sessionDetail.getTaskId(),sessionDetail.getUserId(),
                    sessionDetail.getSessinId(),sessionDetail.getPageid(),sessionDetail.getActionTime(),
                    sessionDetail.getSearchKeyWord(),sessionDetail.getClickCategoryId(),sessionDetail.getClickProductId()
                    ,sessionDetail.getOrderCategoryIds(),sessionDetail.getOrderProductIds(),sessionDetail.getPayCategoryIds(),sessionDetail.getPayProductIds()};
            paramList.add(object);
        }
        JDBCHelper.getInstance().excuteBatch(sql,paramList);
    }
}
