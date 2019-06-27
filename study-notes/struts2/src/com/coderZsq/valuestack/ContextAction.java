package com.coderZsq.valuestack;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ContextAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    // 把数据存储在ValueStack的context区域
    @Override
    public String execute() throws Exception {
        // 方式1
        ActionContext.getContext().getValueStack().getContext().put("username", "Castie!");
        ActionContext.getContext().getValueStack().getContext().put("password", "sha-256");
        // 方式2 上述代码可以简写
        ActionContext.getContext().put("github", "https://github.com/coderZsq");

        // 把数据共享在session中
        ActionContext.getContext().getSession().put("USER_IN_SESSION", "coderZsq");
        return SUCCESS;
    }
}
