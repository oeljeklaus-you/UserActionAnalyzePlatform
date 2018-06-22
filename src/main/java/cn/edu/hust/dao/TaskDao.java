package cn.edu.hust.dao;

import cn.edu.hust.domain.Task;

public interface TaskDao {
    Task findTaskById(Long id);
}
