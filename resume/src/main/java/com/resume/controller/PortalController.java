package com.resume.controller;

import com.resume.common.ServerResponse;
import com.resume.service.IPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhushuangquan on 08/11/2017.
 */

@Controller
@RequestMapping("/portal/")
public class PortalController {

    @Autowired
    private IPortalService iPortalService;

    @RequestMapping("fetch_profile.do")
    @ResponseBody
    public ServerResponse fetchProfile(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return iPortalService.fetchProfile();
    }

    @RequestMapping("fetch_projects.do")
    @ResponseBody
    public ServerResponse fetchProjects(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return iPortalService.fetchProjects();
    }


    @RequestMapping("fetch_github.do")
    @ResponseBody
    public ServerResponse fetchGitHub(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return iPortalService.fetchGitHub();
    }
}
