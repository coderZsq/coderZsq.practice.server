package com.resume.controller;

import com.resume.common.ServerResponse;
import com.resume.service.IBackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhushuangquan on 15/12/2017.
 */
@Controller
@RequestMapping("/backend/")
public class BackendController {

    @Autowired
    private IBackendService iBackendService;

    @RequestMapping("update_profile.do")
    @ResponseBody
    public ServerResponse updateProfile(HttpServletResponse response,String profile_image, String profile_name,  String profile_career, String profile_location, String profile_summary_title, String profile_summary_description, String profile_interest_title, String profile_education_title) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return iBackendService.updateProfile(profile_image, profile_name, profile_career, profile_location, profile_summary_title, profile_summary_description, profile_interest_title, profile_education_title);
    }

    @RequestMapping("update_profile_social.do")
    @ResponseBody
    public ServerResponse updateProfileSocial(HttpServletResponse response, String src, String href, Integer id) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return iBackendService.updateProfileSocial(src, href, id);
    }

    @RequestMapping("insert_profile_social.do")
    @ResponseBody
    public ServerResponse insertProfileSocial(HttpServletResponse response, String src, String href) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return iBackendService.insertProfileSocial(src, href);
    }

    @RequestMapping("delete_profile_social.do")
    @ResponseBody
    public ServerResponse deleteProfileSocial(HttpServletResponse response, Integer id) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return iBackendService.deleteProfileSocial(id);
    }
}
