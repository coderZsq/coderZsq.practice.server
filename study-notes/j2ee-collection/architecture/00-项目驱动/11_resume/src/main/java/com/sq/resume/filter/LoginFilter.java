package com.sq.resume.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String uri = request.getRequestURI();
        // 优先放开的请求
        if (uri.contains("/asset/")) {
            chain.doFilter(request, response);
        } else if (uri.contains("/admin") || uri.contains("/save") || uri.contains("/remove")) {
            // 需要做登录验证的请求
            Object user = request.getSession().getAttribute("user");
            if (user != null) { // 登录成功过
                chain.doFilter(request, response);
            } else { // 没有登录成功过
                response.sendRedirect(request.getContextPath() + "/page/login.jsp");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
