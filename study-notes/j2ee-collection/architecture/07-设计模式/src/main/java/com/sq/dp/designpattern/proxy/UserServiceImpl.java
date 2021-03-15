package com.sq.dp.designpattern.proxy;

public class UserServiceImpl implements IUserService {
    @Override
    public boolean login(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        return username.equals("root") && password.equals("admin");
    }
}
