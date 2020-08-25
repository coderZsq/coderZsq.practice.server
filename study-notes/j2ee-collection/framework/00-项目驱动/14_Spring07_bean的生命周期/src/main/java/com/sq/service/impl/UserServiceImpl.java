package com.sq.service.impl;

import com.sq.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class UserServiceImpl implements
        UserService,
        BeanNameAware, ApplicationContextAware,
        InitializingBean, DisposableBean {
    private Integer age;

    public UserServiceImpl() {
        System.out.println("01 - UserServiceImpl");
    }

    public void setAge(Integer age) {
        this.age = age;
        System.out.println("02 - setAge - " + age);
    }

    @Override
    public boolean login(String username, String password) {
        System.out.println("09 - UserServiceImpl - login - " + username + "_" + password);
        return false;
    }

    public void init() {
        System.out.println("07 - init-method");
    }

    public void dealloc() {
        System.out.println("11 - destroy-method");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("03 - BeanNameAware - setBeanName - " + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("04 - ApplicationContextAware - setApplicationContext - " + applicationContext);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("10 - DisposableBean - destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("06 - InitializingBean - afterPropertiesSet");
    }
}
