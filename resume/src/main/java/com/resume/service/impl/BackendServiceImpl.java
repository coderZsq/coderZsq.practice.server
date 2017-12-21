package com.resume.service.impl;

import com.resume.common.ServerResponse;
import com.resume.dao.ProfileEducationMapper;
import com.resume.dao.ProfileInterestMapper;
import com.resume.dao.ProfileMapper;
import com.resume.dao.ProfileSocialMapper;
import com.resume.pojo.ProfileSocial;
import com.resume.service.IBackendService;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhushuangquan on 15/12/2017.
 */
@Service("iBackendService")
public class BackendServiceImpl implements IBackendService {

    @Autowired
    private ProfileMapper profileMapper;

    public ServerResponse updateProfile(String profileImage, String profileName,  String profileCareer, String profileLocation, String profileSummaryTitle, String profileSummaryDescription, String profileInterestTitle, String profileEducationTitle) {

        int rowCount = profileMapper.updateProfile(profileImage, profileName, profileCareer, profileLocation, profileSummaryTitle, profileSummaryDescription, profileInterestTitle, profileEducationTitle);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("update success");
        }
        return ServerResponse.createBySuccess("update fail");
    }

    @Autowired
    private ProfileSocialMapper profileSocialMapper;

    public ServerResponse updateProfileSocial(String src, String href, Integer id) {
        int rowCount = profileSocialMapper.updateProfileSocial(src, href, id);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("update success");
        }
        return ServerResponse.createBySuccess("update fail");
    }

    public ServerResponse insertProfileSocial(String src, String href) {
        int rowCount = profileSocialMapper.insertProfileSocial(src, href);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("insert success");
        }
        return ServerResponse.createBySuccess("insert fail");
    }

    public ServerResponse deleteProfileSocial(Integer id) {
        int rowCount = profileSocialMapper.deleteByPrimaryKey(id);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("delete success");
        }
        return ServerResponse.createBySuccess("delete fail");
    }

    @Autowired
    private ProfileInterestMapper profileInterestMapper;

    public ServerResponse updateProfileInterest(String interest, Integer id) {
        int rowCount = profileInterestMapper.updateProfileInterest(interest, id);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("update success");
        }
        return ServerResponse.createBySuccess("update fail");
    }

    public ServerResponse insertProfileInterest(String interest) {
        int rowCount = profileInterestMapper.insertProfileInterest(interest);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("insert success");
        }
        return ServerResponse.createBySuccess("insert fail");
    }

    public ServerResponse deleteProfileInterest(Integer id) {
        int rowCount = profileInterestMapper.deleteByPrimaryKey(id);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("delete success");
        }
        return ServerResponse.createBySuccess("delete fail");
    }

    @Autowired
    private ProfileEducationMapper profileEducationMapper;

    public ServerResponse updateProfileEducation(String major, String school, String year, Integer id) {
        int rowCount = profileEducationMapper.updateProfileEducation(major, school, year, id);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("update success");
        }
        return ServerResponse.createBySuccess("update fail");
    }

    public ServerResponse insertProfileEducation(String major, String school, String year) {
        int rowCount = profileEducationMapper.insertProfileEducation(major, school, year);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("insert success");
        }
        return ServerResponse.createBySuccess("insert fail");
    }

    public ServerResponse deleteProfileEducation(Integer id) {
        int rowCount = profileEducationMapper.deleteByPrimaryKey(id);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("delete success");
        }
        return ServerResponse.createBySuccess("delete fail");
    }
}
