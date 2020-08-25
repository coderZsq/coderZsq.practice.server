package com.sq.service.impl;

import com.sq.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class UserServiceImpl implements UserService {
    @Override
    public boolean login(String username, String password) {
        System.out.println("UserServiceImpl - login");
        return false;
    }

    @Override
    public boolean register(String username, String password) {
        System.out.println("UserServiceImpl - register");
        return false;
    }
}
