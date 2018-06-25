package cn.edu.hust.dao;

import cn.edu.hust.domain.Top10CategorySession;

import java.util.List;

public interface Top10CategorySessionDao {
    void batchInsert(List<Top10CategorySession> top10CategorySessionList);
}
