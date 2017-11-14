package com.resume.dao;

import com.resume.pojo.Column;

import java.util.List;

public interface ColumnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Column record);

    int insertSelective(Column record);

    Column selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Column record);

    int updateByPrimaryKey(Column record);

    List<Column>selectAllColumn();
}