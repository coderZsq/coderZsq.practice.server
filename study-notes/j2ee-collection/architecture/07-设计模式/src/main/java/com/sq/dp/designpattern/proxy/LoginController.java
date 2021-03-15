package com.sq.dp.designpattern.proxy;

public class LoginController {
    private IUserService userService;

    public LoginController(IUserService userService) {
        this.userService = userService;
    }

    public String login(String username, String password) {
        boolean ret = userService.login(username, password);
        return ret ? "登录成功" : "登录失败";
    }
}
