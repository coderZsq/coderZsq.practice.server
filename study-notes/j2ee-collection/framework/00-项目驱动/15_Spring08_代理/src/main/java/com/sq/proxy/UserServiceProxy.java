package com.sq.proxy;

import com.sq.service.UserService;

public class UserServiceProxy implements UserService {
    private UserService target;

    public void setTarget(UserService target) {
        this.target = target;
    }

    @Override
    public boolean login(String username, String password) {
        System.out.println("日志---------------------1");

        boolean result = target.login(username, password);

        System.out.println("日志---------------------2");
        return result;
    }
}
