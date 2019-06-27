package com.coderZsq.valuestack;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RootAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    public String getUsername() {
        return "Castie!";
    }

    private String password;
    // 属性: password
    public String getPassword() {
        return password;
    }

    // 把数据存储在ValueStack的root区域
    @Override
    public String execute() throws Exception {
        // 方式1
//        ActionContext.getContext().getValueStack().getRoot().add(0, "0");
        // 方式2
//        ActionContext.getContext().getValueStack().getRoot().push("1");
        // 方式3
//        ActionContext.getContext().getValueStack().set("welcome", "coderZsq");
        // 方式4 直接在当前Action中提供一个属性(getter)

        password = "sha-256";
        return SUCCESS;
    }
}
