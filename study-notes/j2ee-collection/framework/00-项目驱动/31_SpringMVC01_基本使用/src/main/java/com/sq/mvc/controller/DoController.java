package com.sq.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DoController {
    @RequestMapping("/abc/test.do")
    @ResponseBody
    public String test() {
        return "Test";
    }

    // @RequestMapping("/test.html")
    // @ResponseBody
    // public String test2() {
    //     return "test.html";
    // }

    @RequestMapping("/{filename}.jsp")
    @ResponseBody
    public String test3(@PathVariable String filename) {
        return filename;
    }
}
