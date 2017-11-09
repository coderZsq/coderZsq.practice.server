package com.resume.controller;

import com.resume.common.ServerResponse;
import com.resume.service.IPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhushuangquan on 08/11/2017.
 */

@Controller
@RequestMapping("/portal/")
public class PortalController {

    @Autowired
    private IPortalService iPortalService;

    @RequestMapping("fetch.do")
    @ResponseBody
    public ServerResponse fetch() {
        return iPortalService.fetch();
    }
}
