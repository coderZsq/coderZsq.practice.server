package com.coderZsq.params;

import com.opensymphony.xwork2.ActionSupport;

// 第一种: Action本身作为Model对象, 通过setter方法封装(属性注入)
public class LoginAction1 extends ActionSupport {
    private static final long serialVersionUID = 1L;

    // 字段
    private String username;
    private String password;

    // 属性: username
    public void setUsername(String username) {
        this.username = username;
    }

    // 属性: password
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String execute() throws Exception {
        System.out.println(username + ", " + password);
        return NONE;
    }
}
