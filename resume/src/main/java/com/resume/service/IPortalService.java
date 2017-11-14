package com.resume.service;

import com.resume.common.ServerResponse;
import com.resume.vo.*;

/**
 * Created by zhushuangquan on 09/11/2017.
 */
public interface IPortalService {

    ServerResponse<ProfileVo> fetchProfile();

    ServerResponse<ProjectsVo> fetchProjects();

    ServerResponse<GitHubVo>fetchGitHub();

    ServerResponse<ArticlesVo> fetchArticles();

    ServerResponse<ExperienceVo> fetchExperience();

    ServerResponse<ContactVo> fetchContact();
}
