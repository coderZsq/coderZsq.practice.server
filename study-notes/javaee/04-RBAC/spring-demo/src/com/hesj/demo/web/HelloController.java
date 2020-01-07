package com.hesj.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hesj
 * @Created by 小码哥教育 hesj
 */
@Controller
public class HelloController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("hello, SpringMVC");
        return "success";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(String username,String password){
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        return "success";
    }
}
