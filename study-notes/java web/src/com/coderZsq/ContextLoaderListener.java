package com.coderZsq;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

// 监听系统(应用)的启动和销毁
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Web系统启动了...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Web系统销毁了...");
    }
}
