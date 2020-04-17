package com.coderZsq;

import com.coderZsq.domain.anno.BoyFriend;
import com.coderZsq.domain.xml.GirlFriend;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 方式一: 使用xml中配置的bean标签, 在IoC容器中注入bean对象
 * 方式二: 使用包扫描路径 Component-scan 扫描指定包(及其子包)的bean对象
 */
public class XmlApp {
    public static void main(String[] args) {
        // 1. 加载配置文件, 创建IOC容器
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        // new AnnotationConfigApplicationContext()

        // 2. 从容器中获取bean对象
        GirlFriend lucy = ctx.getBean(GirlFriend.class);
        GirlFriend lucy2 = ctx.getBean("lucy", GirlFriend.class);
        System.out.println(lucy);
        System.out.println(lucy2);

        System.out.println("===================");
        BoyFriend jack = ctx.getBean(BoyFriend.class);
        BoyFriend lilei = ctx.getBean("lilei", BoyFriend.class);
        System.out.println(jack);
        System.out.println(lilei);
        // 3. 销毁容器
        ctx.destroy();
    }
}