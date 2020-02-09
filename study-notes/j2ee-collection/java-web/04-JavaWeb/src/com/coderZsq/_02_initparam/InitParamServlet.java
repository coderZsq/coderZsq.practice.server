package com.coderZsq._02_initparam;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

// 获取Servlet的初始化参数
public class InitParamServlet implements Servlet {
    private ServletConfig config;

    // 做初始化
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        // 获取参数名为encoding的初始化参数
        String encoding = config.getInitParameter("encoding");
        System.out.println(encoding);
        // 获取所有参数的名字和值
        Enumeration<String> en = config.getInitParameterNames();
        while (en.hasMoreElements()) {
            String paramName = en.nextElement();
            System.out.println(paramName + "," + config.getInitParameter(paramName));
        }
        if ("GBK".equals(encoding)) {
            System.out.println("你好世界");
        } else {
            System.out.println("Hello World");
        }
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
