package com.resume.service;

import com.resume.common.ServerResponse;
import com.resume.vo.ArticlesVo;
import com.resume.vo.GitHubVo;
import com.resume.vo.ProfileVo;
import com.resume.vo.ProjectsVo;

/**
 * Created by zhushuangquan on 09/11/2017.
 */
public interface IPortalService {

    ServerResponse<ProfileVo> fetchProfile();

    ServerResponse<ProjectsVo> fetchProjects();

    ServerResponse<GitHubVo>fetchGitHub();

    ServerResponse<ArticlesVo> fetchArticles();
}
