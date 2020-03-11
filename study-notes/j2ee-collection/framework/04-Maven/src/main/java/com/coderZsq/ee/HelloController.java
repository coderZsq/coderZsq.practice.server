package com.coderZsq.ee;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping(value = "hello", produces = "text/json;charset=utf-8")
    public String hello(String name) {
        System.out.println("你好" + name);
        return "你好" + name;
    }
}
