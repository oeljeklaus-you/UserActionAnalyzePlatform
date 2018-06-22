package cn.edu.hust.dao.factory;

import cn.edu.hust.dao.TaskDao;
import cn.edu.hust.dao.impl.TaskDaoImpl;

public class DaoFactory {
    /**
     * 使用工厂模式
     * @return
     */
    public static TaskDao getTaskDao()
    {
        return new TaskDaoImpl();
    }
}
