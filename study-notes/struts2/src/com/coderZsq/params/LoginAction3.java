package com.coderZsq.params;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

// 第三种: 使用ModelDriven接口, 对请求数据进行封装 (模型驱动)
public class LoginAction3 extends ActionSupport implements ModelDriven<User> {
    private static final long serialVersionUID = 1L;

    private User user = new User();

    @Override
    public String execute() throws Exception {
        System.out.println(user);
        return NONE;
    }

    @Override
    public User getModel() {
        return user;
    }
}
