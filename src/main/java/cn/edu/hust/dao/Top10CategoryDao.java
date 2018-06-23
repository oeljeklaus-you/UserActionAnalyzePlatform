package cn.edu.hust.dao;

import cn.edu.hust.domain.Top10Category;

import java.util.List;

public interface Top10CategoryDao {
    void insert(Top10Category top10Category);
    void batchInsert(List<Top10Category> top10CategoryList);
}
