package com.sq.dp.designpattern.proxy;

public class LoginProxy implements IUserService {
    private IUserService userService;

    public LoginProxy(IUserService userService) {
        this.userService = userService;
    }

    public void preLogin(String username, String password) {
        System.out.println("[INFO] 用户: " + username + " 准备登录了...");
    }

    @Override
    public boolean login(String username, String password) {
        this.preLogin(username, password);

        boolean ret = userService.login(username, password);

        this.postLogin(username, password, ret);
        return ret;
    }

    public void postLogin(String username, String password, boolean ret) {
        System.out.println("[INFO] 用户: " + username + " 登录结果: " + ret);
    }
}
