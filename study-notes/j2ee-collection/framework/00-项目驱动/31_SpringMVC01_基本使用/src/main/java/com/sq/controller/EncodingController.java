package com.sq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/encoding")
public class EncodingController {
    @RequestMapping("/login")
    @ResponseBody
    public String login(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        return "login success!";
    }

    @RequestMapping(value = "/get", produces = "text/plain")
    @ResponseBody
    public String get() {
        return "<h1>This is 街舞</h1>";
    }

    // @RequestMapping(value = "/get", produces = "text/html; charset=UTF-8")
    // @ResponseBody
    // public String get() {
    //     return "<h1>This is 街舞</h1>";
    // }
}
