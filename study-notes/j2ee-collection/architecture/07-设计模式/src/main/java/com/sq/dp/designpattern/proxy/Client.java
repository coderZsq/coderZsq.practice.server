package com.sq.dp.designpattern.proxy;

public class Client {
    public static void main(String[] args) {
        IUserService userService = new LoginProxy(new UserServiceImpl());
        LoginController controller = new LoginController(userService);
        controller.login("root", "admin");
    }
}
