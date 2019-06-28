package com.coderZsq.tag;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

// 把多个数据存放在root区域
public class ListAction2 extends ActionSupport {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute() throws Exception {
        ActionContext.getContext().put("students", Student.listAll());
        return SUCCESS;
    }
}
