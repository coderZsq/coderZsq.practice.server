package com.resume.dao;

import com.resume.pojo.ProfileEducation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProfileEducationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProfileEducation record);

    int insertSelective(ProfileEducation record);

    ProfileEducation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProfileEducation record);

    int updateByPrimaryKey(ProfileEducation record);

    List<ProfileEducation> selectAllEducation();

    int updateProfileEducation(@Param("major") String major, @Param("school") String school, @Param("year") String year, @Param("id") Integer id);

    int insertProfileEducation(@Param("major") String major, @Param("school") String school, @Param("year") String year);
}