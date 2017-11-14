package com.resume.dao;

import com.resume.pojo.Projects;

import java.util.List;

public interface ProjectsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Projects record);

    int insertSelective(Projects record);

    Projects selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Projects record);

    int updateByPrimaryKey(Projects record);

    List<Projects> selectAllProjects();
}