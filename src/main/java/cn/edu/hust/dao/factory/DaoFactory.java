package cn.edu.hust.dao.factory;

import cn.edu.hust.dao.*;
import cn.edu.hust.dao.impl.*;
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

    public static Top10CategoryDao getTop10CategoryDao(){ return new Top10CategoryDaoImpl();}

    public static Top10CategorySessionDao getTop10CategorySessionDao(){ return new Top10CategorySessionDaoImpl();}
}
