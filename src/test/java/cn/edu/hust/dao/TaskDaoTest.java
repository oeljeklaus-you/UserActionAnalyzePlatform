package cn.edu.hust.dao;

import cn.edu.hust.dao.factory.DaoFactory;
import cn.edu.hust.domain.Task;
import org.junit.Test;

public class TaskDaoTest {
    @Test
    public void testDao()
    {
        Task task=DaoFactory.getTaskDao().findTaskById(1L);
        System.out.println(task.getTaskName()+":"+task.getTaskParam());
    };
}
