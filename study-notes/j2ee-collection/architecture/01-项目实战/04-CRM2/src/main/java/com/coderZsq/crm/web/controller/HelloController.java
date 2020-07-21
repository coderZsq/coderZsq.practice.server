package com.coderZsq.crm.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("hello")
    public boolean hello() {
        System.out.println("true = " + true);
        return true;
    }
}
