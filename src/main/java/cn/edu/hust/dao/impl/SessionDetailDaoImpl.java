package cn.edu.hust.dao.impl;

import cn.edu.hust.dao.SessionDetailDao;
import cn.edu.hust.domain.SessionDetail;
import cn.edu.hust.jdbc.JDBCHelper;

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
}
