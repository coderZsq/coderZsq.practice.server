package com.resume.dao;

import com.resume.pojo.ProfileInterest;

import java.util.List;

public interface ProfileInterestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProfileInterest record);

    int insertSelective(ProfileInterest record);

    ProfileInterest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProfileInterest record);

    int updateByPrimaryKey(ProfileInterest record);

    List<ProfileInterest>selectAllInterest();
}