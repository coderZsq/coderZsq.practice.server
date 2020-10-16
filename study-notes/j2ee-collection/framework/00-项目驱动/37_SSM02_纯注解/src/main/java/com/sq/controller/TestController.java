package com.sq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Controller
public class TestController {
    @GetMapping("/test1")
    public String test1() {
        // /WEB-INF/page/test.jsp
        return "test";
    }

    @GetMapping("/test2")
    @ResponseBody
    public String test2() {
        return "测试中文数据";
    }

    @GetMapping("/test3")
    @ResponseBody
    public String test3(@RequestParam("birthday") Date birthday) {
        return birthday.toString();
    }

    @PostMapping("/test4")
    @ResponseBody
    public String test4(@RequestParam("name") String name,
                        @RequestParam("photo") MultipartFile photo) {
        return "Success - " + name + " - " + photo.toString();
    }
}
