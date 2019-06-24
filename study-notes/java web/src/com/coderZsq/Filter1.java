package com.coderZsq;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class Filter1 implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        System.out.println("被过滤资源=" + request.getRequestURI());
//        System.out.println("Filter1... before...");
        filterChain.doFilter(servletRequest, servletResponse);
//        System.out.println("Filter1... after...");
    }

    @Override
    public void destroy() {

    }
}
