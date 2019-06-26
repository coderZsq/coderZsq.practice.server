package com.coderZsq.params;

import com.opensymphony.xwork2.ActionSupport;

// 第二种: 创建独立Model对象, 页面通过ognl表达式封装(属性注入)
public class LoginAction2 extends ActionSupport {
    private static final long serialVersionUID = 1L;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String execute() throws Exception {
        System.out.println(user);
        return NONE;
    }
}
