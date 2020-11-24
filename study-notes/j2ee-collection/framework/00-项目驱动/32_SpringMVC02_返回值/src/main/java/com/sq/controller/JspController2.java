package com.sq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/test")
public class JspController2 {
    @RequestMapping("/jsp1")
    public String jsp1() {
        return "forward:/page/jsp1.jsp";
    }

    @RequestMapping("/jsp2")
    public ModelAndView jsp2() {
        return new ModelAndView("jsp7");
    }

    @RequestMapping("/jsp3")
    public ModelAndView jsp3() {
        ModelAndView mv = new ModelAndView();
        /*
            InternalResourceView: 转发
            JstlView: 转发
            RedirectView: 重定向
         */
        mv.setView(new RedirectView("/page/jsp3.jsp"));

        return mv;
    }
}
