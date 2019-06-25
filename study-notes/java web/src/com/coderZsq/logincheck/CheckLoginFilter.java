package com.coderZsq.logincheck;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class CheckLoginFilter implements javax.servlet.Filter {

    // 不需要检查的资源
    private String[] unCheckUris = {"/login.jsp", "/login"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        System.out.println("当前正在过滤的资源: " + request.getRequestURI());
        // 当前正在过滤的资源
        String requestUri = request.getRequestURI();
        if (!Arrays.asList(unCheckUris).contains(requestUri)) {
            Object user = request.getSession().getAttribute("USER_IN_SESSION");
            if (user == null) { // 没有登录
                response.sendRedirect("/login.jsp");
                return;
            }
        }
        filterChain.doFilter(request, response); // 放行
    }

    @Override
    public void destroy() {

    }
}
