package com.coderzsq._10_yaml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {
    // @Value("${name}")
    private String name;

    // @Value("${scores}")
    private String[] scores;

    // @Value("${info}")
    private Map<String, String> info;

    @Autowired
    private ApplicationContext context;

    @RequestMapping("hello")
    public String hello() {
        System.out.println(context.getBeansOfType(ConfigProperties.class).size());
        ConfigProperties bean = context.getBean(ConfigProperties.class);
        System.out.println("bean = " + bean);
        return "success";
    }
}
