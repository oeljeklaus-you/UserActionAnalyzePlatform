package cn.edu.hust.dao.factory;

import cn.edu.hust.dao.SessionAggrStatDao;
import cn.edu.hust.dao.SessionDetailDao;
import cn.edu.hust.dao.SessionRandomExtractDao;
import cn.edu.hust.dao.TaskDao;
import cn.edu.hust.dao.impl.SessionAggrStatDaoImpl;
import cn.edu.hust.dao.impl.SessionDetailDaoImpl;
import cn.edu.hust.dao.impl.SessionRandomExtractDaoImpl;
import cn.edu.hust.dao.impl.TaskDaoImpl;
import cn.edu.hust.domain.SessionDetail;
import cn.edu.hust.domain.SessionRandomExtract;

public class DaoFactory {
    /**
     * 使用工厂模式
     * @return
     */
    public static TaskDao getTaskDao()
    {
        return new TaskDaoImpl();
    }

    public static SessionAggrStatDao getSessionAggrStatDao()
    {
        return new SessionAggrStatDaoImpl();
    }

    public static SessionRandomExtractDao getSessionRandomExtractDao(){
        return new SessionRandomExtractDaoImpl();
    }

    public  static SessionDetailDao getSessionDetailDao()
    {
        return new SessionDetailDaoImpl();
    }
}
