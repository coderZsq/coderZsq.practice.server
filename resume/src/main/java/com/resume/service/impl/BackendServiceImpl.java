package com.resume.service.impl;

import com.resume.common.ServerResponse;
import com.resume.dao.ProfileMapper;
import com.resume.dao.ProfileSocialMapper;
import com.resume.service.IBackendService;
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
            return ServerResponse.createBySuccess("update success");
        }
        return ServerResponse.createBySuccess("update fail");
    }

    public ServerResponse deleteProfileSocial(Integer id) {
        int rowCount = profileSocialMapper.deleteByPrimaryKey(id);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("update success");
        }
        return ServerResponse.createBySuccess("update fail");
    }
}
