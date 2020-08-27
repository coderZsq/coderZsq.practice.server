package com.sq.service.impl;

import com.sq.annotation.Log;
import com.sq.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class UserServiceImpl implements UserService, BeanNameAware, ApplicationContextAware {
    private ApplicationContext ctx;
    private String beanName;

    @Override
    public boolean login(String username, String password) {
        System.out.println("UserServiceImpl - login");
        // ctx.getBean(beanName, UserService.class).register(username, password);
        return false;
    }

    @Override
    @Log
    public boolean register(String username) {
        System.out.println("UserServiceImpl - register");
        return false;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        beanName = name;
    }
}
