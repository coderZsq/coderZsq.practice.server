package com.coderZsq.servletapi;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 方式1: 通过让Action类去实现感知接口
public class ServletApiAction1 extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    private static final long serialVersionUID = 1L;

    private HttpServletRequest request;
    private HttpServletResponse response;

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    @Override
    public String execute() throws Exception {
        System.out.println("..." + request.getParameter("name") + response);
        return NONE;
    }

}
