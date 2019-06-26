package com.coderZsq.servletapi;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

// 方式2: 使用ServletActionContext类, 该类提供很多静态方法可以返回Servlet API对象
public class ServletApiAction2 extends ActionSupport {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute() throws Exception {
        System.out.println("..." + ServletActionContext.getRequest().getParameter("name"));
        ServletActionContext.getRequest().setAttribute("requestName", "requestValue");
        ServletActionContext.getRequest().getSession().setAttribute("sessionName", "sessionValue");
        return SUCCESS;
    }

}
