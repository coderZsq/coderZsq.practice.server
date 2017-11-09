package com.resume.service.impl;

import com.google.common.collect.Lists;
import com.resume.common.ServerResponse;
import com.resume.dao.ProfileEducationMapper;
import com.resume.dao.ProfileInterestMapper;
import com.resume.dao.ProfileMapper;
import com.resume.pojo.Profile;
import com.resume.pojo.ProfileEducation;
import com.resume.pojo.ProfileInterest;
import com.resume.service.IPortalService;
import com.resume.vo.ProfileEducationVo;
import com.resume.vo.ProfileInterestVo;
import com.resume.vo.ProfileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhushuangquan on 09/11/2017.
 */

@Service("iPortalService")
public class PortalServiceImpl implements IPortalService {

    @Autowired
    private ProfileMapper profileMapper;

    @Autowired
    private ProfileInterestMapper profileInterestMapper;

    @Autowired
    private ProfileEducationMapper profileEducationMapper;

    public ServerResponse fetchProfile() {

        ProfileVo profileVo = assembleProfileVo(profileMapper.selectAll());
        List<ProfileInterest> profileInterestList = profileInterestMapper.selectAllInterest();
        List<ProfileInterestVo> profileInterestVoList = Lists.newArrayList();
        List<ProfileEducation> profileEducationList = profileEducationMapper.selectAllEducation();
        List<ProfileEducationVo> profileEducationVoList = Lists.newArrayList();
        for (ProfileInterest profileInterest: profileInterestList) {
            ProfileInterestVo profileInterestVo = assembleProfileInterestVo(profileInterest);
            profileInterestVoList.add(profileInterestVo);
        }
        for (ProfileEducation profileEducation: profileEducationList) {
            ProfileEducationVo profileEducationVo = assembleProfileEducationVo(profileEducation);
            profileEducationVoList.add(profileEducationVo);
        }
        profileVo.setProfileInterestList(profileInterestVoList);
        profileVo.setProfileEducationList(profileEducationVoList);
        return ServerResponse.createBySuccess(profileVo);
    }

    private ProfileVo assembleProfileVo(Profile profile) {
        ProfileVo profileVo = new ProfileVo();
        profileVo.setProfileImage(profile.getProfileImage());
        profileVo.setProfileInterestTitle(profile.getProfileInterestTitle());
        profileVo.setProfileCareer(profile.getProfileCareer());
        profileVo.setProfileName(profile.getProfileName());
        profileVo.setProfileEducationTitle(profile.getProfileEducationTitle());
        profileVo.setProfileLocation(profile.getProfileLocation());
        profileVo.setProfileSummaryTitle(profile.getProfileSummaryTitle());
        profileVo.setProfileSummaryDescription(profile.getProfileSummaryDescription());
        return profileVo;
    }

    private ProfileInterestVo assembleProfileInterestVo(ProfileInterest profileInterest) {
        ProfileInterestVo profileInterestVo = new ProfileInterestVo();
        profileInterestVo.setInterest(profileInterest.getInterest());
        return profileInterestVo;
    }

    private ProfileEducationVo assembleProfileEducationVo(ProfileEducation profileEducation) {
        ProfileEducationVo profileEducationVo = new ProfileEducationVo();
        profileEducationVo.setMajor(profileEducation.getMajor());
        profileEducationVo.setSchool(profileEducation.getSchool());
        profileEducationVo.setYear(profileEducation.getYear());
        return profileEducationVo;
    }
}

