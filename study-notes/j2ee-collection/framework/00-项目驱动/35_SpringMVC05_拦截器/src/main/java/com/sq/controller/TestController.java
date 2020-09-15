package com.sq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @RequestMapping("/test1")
    @ResponseBody
    public String test1() {
        System.out.println("test1--------------");
        return "test1 success!";
    }

    @RequestMapping("/test2")
    public String test2() {
        System.out.println("test2--------------");
        return "/test1.jsp";
    }
}
