package cn.edu.hust.dao;

import cn.edu.hust.dao.factory.DaoFactory;
import cn.edu.hust.domain.SessionRandomExtract;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SessionRandomExtractDaoTest {
    @Test
    public void testBatchInsert()
    {
        List<SessionRandomExtract> sessionRandomExtractList=new ArrayList<SessionRandomExtract>();
        SessionRandomExtract sessionRandomExtract1=new SessionRandomExtract();
        sessionRandomExtract1.set(1L,"1","2","3","4");
        SessionRandomExtract sessionRandomExtract2=new SessionRandomExtract();
        sessionRandomExtract2.set(2L,"1","2","3","4");

        sessionRandomExtractList.add(sessionRandomExtract1);
        sessionRandomExtractList.add(sessionRandomExtract2);
        DaoFactory.getSessionRandomExtractDao().batchInsert(sessionRandomExtractList);
    }
}
