package com.coderZsq._03_extends;

import javax.servlet.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;

abstract class MyGenericServlet implements Serializable, Servlet, ServletConfig {
    private static final long serialVersionUID = 1L;
    private ServletConfig config;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = servletConfig;
        init();
    }

    // 专门暴露给子类, 让子类编写自身的初始化代码
    public void init() {
        // NO OP
    }

    @Override
    abstract public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException;

    // 把ServletConfig对象暴露给子类访问
    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
    // ------------------------------
    @Override
    public String getServletName() {
        return config.getServletName();
    }

    @Override
    public ServletContext getServletContext() {
        return config.getServletContext();
    }

    @Override
    public String getInitParameter(String s) {
        return config.getInitParameter(s);
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return config.getInitParameterNames();
    }
}
