package com.sq.controller;


import com.sq.service.SkillService;

import java.util.Properties;

public class SkillController {
    private SkillService service;

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        ClassLoader loader = SkillController.class.getClassLoader();
        properties.load(loader.getResourceAsStream("db.properties"));
        System.out.println(properties.get("name"));
        properties.clone();
    }
}
