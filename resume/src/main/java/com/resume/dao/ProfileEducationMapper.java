package com.resume.dao;

import com.resume.pojo.ProfileEducation;

import java.util.List;

public interface ProfileEducationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProfileEducation record);

    int insertSelective(ProfileEducation record);

    ProfileEducation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProfileEducation record);

    int updateByPrimaryKey(ProfileEducation record);

    List<ProfileEducation> selectAllEducation();
}