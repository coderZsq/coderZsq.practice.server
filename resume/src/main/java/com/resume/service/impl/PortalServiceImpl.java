package com.resume.service.impl;

import com.google.common.collect.Lists;
import com.resume.common.ServerResponse;
import com.resume.dao.*;
import com.resume.pojo.*;
import com.resume.service.IPortalService;
import com.resume.vo.*;
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
    private ProfileSocialMapper profileSocialMapper;

    @Autowired
    private ProfileInterestMapper profileInterestMapper;

    @Autowired
    private ProfileEducationMapper profileEducationMapper;

    public ServerResponse<ProfileVo> fetchProfile() {

        ProfileVo profileVo = assembleProfileVo(profileMapper.selectAll());
        List<ProfileSocial> profileSocialList = profileSocialMapper.selectAllSocial();
        List<ProfileSocialVo> profileSocialVoList = Lists.newArrayList();
        List<ProfileInterest> profileInterestList = profileInterestMapper.selectAllInterest();
        List<ProfileInterestVo> profileInterestVoList = Lists.newArrayList();
        List<ProfileEducation> profileEducationList = profileEducationMapper.selectAllEducation();
        List<ProfileEducationVo> profileEducationVoList = Lists.newArrayList();
        for (ProfileSocial profileSocial: profileSocialList) {
            ProfileSocialVo profileSocialVo = assembleProfileSocialVo(profileSocial);
            profileSocialVoList.add(profileSocialVo);
        }
        for (ProfileInterest profileInterest: profileInterestList) {
            ProfileInterestVo profileInterestVo = assembleProfileInterestVo(profileInterest);
            profileInterestVoList.add(profileInterestVo);
        }
        for (ProfileEducation profileEducation: profileEducationList) {
            ProfileEducationVo profileEducationVo = assembleProfileEducationVo(profileEducation);
            profileEducationVoList.add(profileEducationVo);
        }
        profileVo.setProfileSocialList(profileSocialVoList);
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

    private ProfileSocialVo assembleProfileSocialVo(ProfileSocial profileSocial) {
        ProfileSocialVo profileSocialVo = new ProfileSocialVo();
        profileSocialVo.setSrc(profileSocial.getSrc());
        profileSocialVo.setHref(profileSocial.getHref());
        return profileSocialVo;
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

    @Autowired
    private ProjectsMapper projectsMapper;

    public ServerResponse<ProjectsVo> fetchProjects() {
        ProjectsVo projectsVo = new ProjectsVo();
        List<Projects> projectsList = projectsMapper.selectAllProjects();
        List<ProjectVo> projectVoList = Lists.newArrayList();
        for (Projects projects: projectsList) {
            ProjectVo projectVo = assembleProjectsVo(projects);
            projectVoList.add(projectVo);
        }
        projectsVo.setProjectList(projectVoList);
        return ServerResponse.createBySuccess(projectsVo);
    }

    private ProjectVo assembleProjectsVo(Projects projects) {
        ProjectVo projectVo = new ProjectVo();
        projectVo.setHref(projects.getHref());
        projectVo.setSrc(projects.getSrc());
        projectVo.setDescription(projects.getDescription());
        projectVo.setName(projects.getName());
        projectVo.setText1(projects.getText1());
        projectVo.setText2(projects.getText2());
        projectVo.setText3(projects.getText3());
        return projectVo;
    }

    @Autowired
    private GitHubMapper gitHubMapper;

    public ServerResponse<GitHubVo>fetchGitHub() {
        GitHubVo gitHubVo = new GitHubVo();
        List<GitHub> gitHubList = gitHubMapper.selectAllGitHub();
        List<TargetVo> targetVoList = Lists.newArrayList();
        for (GitHub gitHub: gitHubList) {
            targetVoList.add(assembleTargetVo(gitHub));
        }
        gitHubVo.setTargetList(targetVoList);
        return ServerResponse.createBySuccess(gitHubVo);
    }

    private TargetVo assembleTargetVo(GitHub gitHub) {
        TargetVo targetVo = new TargetVo();
        targetVo.setHref(gitHub.getHref());
        targetVo.setName(gitHub.getName());
        targetVo.setColor(gitHub.getColor());
        targetVo.setDescription(gitHub.getDescription());
        targetVo.setLanguage(gitHub.getLanguage());
        targetVo.setStar(gitHub.getStar());
        return targetVo;
    }

}

