package com.coderZsq;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppClient {
    public static void main(String[] args) {
        // 1 加载配置
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:consumer.xml");
        // 2 启动容器
        context.start();
        IHelloService helloService = (IHelloService) context.getBean("helloService");
        String result = helloService.greet("tom");
        System.out.println("result = " + result);
    }
}
