package cn.edu.hust.dao;

import cn.edu.hust.domain.SessionDetail;

import java.io.Serializable;
import java.util.List;

public interface SessionDetailDao extends Serializable{
    void insert(SessionDetail sessionDetail);
    void batchInsert(List<SessionDetail> sessionDetailList);
}
