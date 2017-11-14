package com.resume.dao;

import com.resume.pojo.GitHub;

import java.util.List;

public interface GitHubMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GitHub record);

    int insertSelective(GitHub record);

    GitHub selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GitHub record);

    int updateByPrimaryKey(GitHub record);

    List<GitHub> selectAllGitHub();
}