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
        profileSocialVo.setId(profileSocial.getId());
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

    @Autowired
    private ColumnMapper columnMapper;

    @Autowired
    private ArticleMapper articleMapper;

    public ServerResponse<ArticlesVo> fetchArticles() {
        ArticlesVo articlesVo = new ArticlesVo();
        List<Column> columnList = columnMapper.selectAllColumn();
        List<ColumnVo> columnVoList = Lists.newArrayList();
        for (Column column: columnList) {
            ColumnVo columnVo = assembleColumnVo(column);
            List<Article> articleList = articleMapper.selectByColumnId(column.getId());
            List<ArticleVo> articleVoList = Lists.newArrayList();
            for (Article article: articleList) {
                articleVoList.add(assembleArticleVo(article));
            }
            columnVo.setArticles(articleVoList);
            columnVoList.add(columnVo);
        }
        articlesVo.setColumnList(columnVoList);
        return ServerResponse.createBySuccess(articlesVo);
    }

    private ColumnVo assembleColumnVo(Column column) {
        ColumnVo columnVo = new ColumnVo();
        columnVo.setName(column.getName());
        return columnVo;
    }

    private ArticleVo assembleArticleVo(Article article) {
        ArticleVo articleVo = new ArticleVo();
        articleVo.setName(article.getName());
        articleVo.setHref(article.getHref());
        return articleVo;
    }

    @Autowired
    private ExperienceMapper experienceMapper;

    public ServerResponse<ExperienceVo> fetchExperience() {
        ExperienceVo experienceVo = new ExperienceVo();
        List<Experience> experienceList = experienceMapper.selectAllExperience();
        List<CareerVo> careerVoList = Lists.newArrayList();
        for (Experience experience: experienceList) {
            careerVoList.add(assembleCareerVo(experience));
        }
        experienceVo.setCareerList(careerVoList);
        return ServerResponse.createBySuccess(experienceVo);
    }

    private CareerVo assembleCareerVo(Experience experience) {
        CareerVo careerVo = new CareerVo();
        careerVo.setHref(experience.getHref());
        careerVo.setCorp(experience.getCorp());
        careerVo.setDesc1(experience.getDesc1());
        careerVo.setDesc2(experience.getDesc2());
        careerVo.setDesc3(experience.getDesc3());
        careerVo.setJob(experience.getJob());
        careerVo.setTime(experience.getTime());
        return careerVo;
    }

    @Autowired
    private ContactMapper contactMapper;

    public ServerResponse<ContactVo> fetchContact() {
        return ServerResponse.createBySuccess(assembleContactVo(contactMapper.selectByPrimaryKey(1)));
    }

    private ContactVo assembleContactVo(Contact contact) {
        ContactVo contactVo = new ContactVo();
        contactVo.setEmail(contact.getEmail());
        contactVo.setMobile(contact.getMobile());
        contactVo.setQq(contact.getQq());
        contactVo.setWechat(contact.getWechat());
        contactVo.setWechatUrl(contact.getWechatUrl());
        return contactVo;
    }
}

