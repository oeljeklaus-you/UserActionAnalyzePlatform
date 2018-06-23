package cn.edu.hust.dao;

import cn.edu.hust.domain.SessionAggrStat;

import java.io.Serializable;

public interface SessionAggrStatDao extends Serializable{
    void insert(SessionAggrStat sessionAggrStat);
}
