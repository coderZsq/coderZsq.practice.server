package com.sq.mvc.controller;

import com.sq.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private Person person;

    @GetMapping("/test")
    public String test() {
        // return "Hello SpringBoot!!!666!!!" + person;
        return "Hello SpringBoot!!!";
    }
}
