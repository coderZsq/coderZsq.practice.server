package com.coderZsq.validation;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import org.apache.struts2.interceptor.validation.SkipValidation;

// 输入校验
public class EmpolyeeAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void validateSave() {
        System.out.println("..输入校验..");
        if (username == null || username.trim().length() < 6) {
            // 把错误信息存储起来
            super.addFieldError("username", "账号不能少于6位");
        }
        if (password == null || password.trim().length() < 6) {
            // 把错误信息存储起来
            super.addFieldError("password", "密码不能少于6位");
        }
    }

    // 进入编辑界面
    public String input() {
        System.out.println("input...");
        return INPUT;
    }

    // 列表
    @SkipValidation
    @Override
    public String execute() throws Exception {
        System.out.println("list...");
        return NONE;
    }

    // 保存
    @InputConfig(resultName = "input")
    public String save() throws Exception {
        System.out.println("save...");
        return NONE;
    }

    // 删除
    public String delete() throws Exception {
        System.out.println("delete...");
        return NONE;
    }
}
