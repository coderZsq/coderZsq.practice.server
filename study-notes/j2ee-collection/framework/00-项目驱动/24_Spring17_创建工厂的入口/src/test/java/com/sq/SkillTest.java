package com.sq;

import com.sq.cfg.BeanConfig;
import com.sq.domain.Dog;
import com.sq.domain.Person;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SkillTest {
    private ApplicationContext ctx;

    @Before
    public void before() {
        // ctx = new AnnotationConfigApplicationContext(Dog.class);
        // ctx = new AnnotationConfigApplicationContext(Dog.class, Person.class, BeanConfig.class);
        // ctx = new AnnotationConfigApplicationContext("com.sq");
        ctx = new AnnotationConfigApplicationContext(BeanConfig.class);
    }

    @Test
    public void test() throws Exception {
        // System.out.println(ctx.getBean("dog"));
        // System.out.println(ctx.getBean("person"));
        // System.out.println(ctx.getBean("beanConfig"));
        System.out.println(ctx.getBean("userService"));
        // System.out.println(ctx.getBean("com.sq.domain.Person"));
        // System.out.println(ctx.getBean("com.sq.cfg.BeanConfig"));
    }
}
