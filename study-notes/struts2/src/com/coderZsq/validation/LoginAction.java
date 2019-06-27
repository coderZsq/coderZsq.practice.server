package com.coderZsq.validation;

import com.opensymphony.xwork2.ActionSupport;

// 输入校验
public class LoginAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 覆盖validation方法, 并编写校验规则
    // 账号和密码不能少于6位
    @Override
    public void validate() {
        if (username == null || !username.matches("^[a-zA-Z]\\w{5,15}$")) {
            System.out.println("--- validate ---");
            // 把错误信息存储起来
            super.addFieldError("username", "账号第一个字必须为字母且在6-16位");
        }
        if (password == null || !password.matches("^[a-zA-Z]\\w{5,15}$")) {
            System.out.println("--- validate ---");
            // 把错误信息存储起来
            super.addFieldError("password", "账号第一个字必须为字母且在6-16位");
        }
    }

    @Override
    public String execute() throws Exception {
        System.out.println(username + ", " + password);
        return NONE;
    }
}
