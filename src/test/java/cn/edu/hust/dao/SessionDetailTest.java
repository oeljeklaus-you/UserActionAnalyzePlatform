package cn.edu.hust.dao;

import cn.edu.hust.dao.factory.DaoFactory;
import cn.edu.hust.domain.SessionDetail;
import org.junit.Test;

public class SessionDetailTest {
    @Test
    public void testInsert()
    {
        SessionDetail sessionDetail=new SessionDetail();
        sessionDetail.set(1L,1L,"1",1L,"1","1",1L,1L,"1","1","1","1");
        DaoFactory.getSessionDetailDao().insert(sessionDetail);
    }
}
