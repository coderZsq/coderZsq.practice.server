package com.coderZsq.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

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

    @Override
    public String execute() throws Exception {
        System.out.println("登录判断");
        // 把登录信息存到session中
        ActionContext.getContext().getSession().put("USER_IN_SESSION", username);
        return SUCCESS;
    }
}
