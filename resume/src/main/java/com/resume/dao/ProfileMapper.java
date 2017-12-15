package com.resume.dao;

import com.resume.pojo.Profile;
import org.apache.ibatis.annotations.Param;

public interface ProfileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Profile record);

    int insertSelective(Profile record);

    Profile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Profile record);

    int updateByPrimaryKey(Profile record);

    Profile selectAll();

    int updateProfile(@Param("profileImage") String profileImage, @Param("profileName") String profileName,  @Param("profileCareer") String profileCareer, @Param("profileLocation") String profileLocation, @Param("profileSummaryTitle") String profileSummaryTitle, @Param("profileSummaryDescription") String profileSummaryDescription, @Param("profileInterestTitle") String profileInterestTitle, @Param("profileEducationTitle") String profileEducationTitle);
}