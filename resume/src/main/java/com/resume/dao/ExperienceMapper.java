package com.resume.dao;

import com.resume.pojo.Experience;

import java.util.List;

public interface ExperienceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Experience record);

    int insertSelective(Experience record);

    Experience selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Experience record);

    int updateByPrimaryKey(Experience record);

    List<Experience> selectAllExperience();
}