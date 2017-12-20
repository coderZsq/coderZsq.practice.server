package com.resume.dao;

import com.resume.pojo.ProfileSocial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProfileSocialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProfileSocial record);

    int insertSelective(ProfileSocial record);

    ProfileSocial selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProfileSocial record);

    int updateByPrimaryKey(ProfileSocial record);

    List<ProfileSocial> selectAllSocial();

    int updateProfileSocial(@Param("src") String src, @Param("href") String href, @Param("id") Integer id);

    int insertProfileSocial(@Param("src") String src, @Param("href") String href);
}