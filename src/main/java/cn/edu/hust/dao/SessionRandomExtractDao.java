package cn.edu.hust.dao;

import cn.edu.hust.domain.SessionRandomExtract;

import java.util.List;

public interface SessionRandomExtractDao {
    void batchInsert(List<SessionRandomExtract> sessionRandomExtractList);
}
