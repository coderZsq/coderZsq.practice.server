package com.sq;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SkillTest {
    private ApplicationContext ctx;

    @Before
    public void before() {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void test() {
        System.out.println("----------------------------------");
        for (String name : ctx.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        System.out.println("----------------------------------");
    }
}
