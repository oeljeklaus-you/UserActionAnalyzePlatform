package cn.edu.hust.dao;

import cn.edu.hust.dao.factory.DaoFactory;
import cn.edu.hust.domain.SessionAggrStat;
import org.junit.Test;

public class SessionAggrDao {
    @Test
    public void test()
    {
        SessionAggrStat sessionAggrStat=new SessionAggrStat();
        sessionAggrStat.set(1L,100L,1,2,3,4,5,6,7,8,9,0,10,11,12,13,14);
        DaoFactory.getSessionAggrStatDao().insert(sessionAggrStat);
    }
}
