package com.resume.dao;

import com.resume.pojo.ProfileSocial;

import java.util.List;

public interface ProfileSocialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProfileSocial record);

    int insertSelective(ProfileSocial record);

    ProfileSocial selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProfileSocial record);

    int updateByPrimaryKey(ProfileSocial record);

    List<ProfileSocial> selectAllSocial();

}