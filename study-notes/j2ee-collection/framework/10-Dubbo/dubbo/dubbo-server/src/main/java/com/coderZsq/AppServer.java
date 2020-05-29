package com.coderZsq;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppServer {
    public static void main(String[] args) throws Exception {
        //1 加载配置
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:provider.xml");
        //2 启动容器
        context.start();
        //3 阻塞, 服务一直运行
        System.in.read();
    }
}
