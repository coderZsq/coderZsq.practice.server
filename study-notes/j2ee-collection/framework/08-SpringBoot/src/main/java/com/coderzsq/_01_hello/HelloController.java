package com.coderzsq._01_hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private ApplicationArguments arguments;

    @RequestMapping("hello")
    public String hello() {
        System.out.println(arguments.getNonOptionArgs());
        return "hello springboot";
    }
}
