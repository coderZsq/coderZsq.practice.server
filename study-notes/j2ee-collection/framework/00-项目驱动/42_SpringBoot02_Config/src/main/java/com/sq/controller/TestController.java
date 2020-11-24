package com.sq.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {
    @Value("${names}")
    private String[] names;
    @Value("#{${homes}}")
    private Map<String, String> homes;

    @GetMapping("/test")
    public String test() {
        homes.forEach((k, v) -> System.out.println(k + "_" + v));
        return "test_";
    }
}