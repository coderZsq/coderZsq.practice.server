package com.coderZsq._30_checklogin;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class CheckLoginFilter implements Filter {
    // 不需要检查的资源
    private String[] unCheckUris = {"/login.jsp", "/login"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // 当前正在过滤的资源
        String requestUri = req.getRequestURI();
        if (!Arrays.asList(unCheckUris).contains(requestUri)) {
            Object user = req.getSession().getAttribute("USER_IN_SESSION");
            if (user == null) { // 没有登录
                resp.sendRedirect("/login.jsp");
                return;
            }
        }
        filterChain.doFilter(req, resp); // 放行
    }

    @Override
    public void destroy() {

    }
}
