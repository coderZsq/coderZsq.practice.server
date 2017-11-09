package com.resume.dao;

import com.resume.pojo.Profile;

public interface ProfileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Profile record);

    int insertSelective(Profile record);

    Profile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Profile record);

    int updateByPrimaryKey(Profile record);

    Profile selectAll();
}