package com.coderZsq.servletapi;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

// 方式3: 使用ActionContext类, 本身是Struts2对Serlet API的封装
public class ServletApiAction3 extends ActionSupport {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute() throws Exception {
        // 获取请求参数
        Map<String, Object> parameterMap = ActionContext.getContext().getParameters();
        // request 作用域
        ActionContext.getContext().put("requestName", "requestValue");
        // session 作用域
        ActionContext.getContext().getSession().put("sessionName", "sessionValue");
        // application/servletContext 作用域
        ActionContext.getContext().getApplication().put("applicationName", "applicationValue");
        return SUCCESS;
    }

}
