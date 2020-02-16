package com.coderZsq._28_filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class FilterDemo1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("FilterDemo1.init()");
    }

    // 执行过滤
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FilterDemo1...before");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("被过滤资源=" + request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse); // 放行操作
        System.out.println("FilterDemo1...after");
    }

    @Override
    public void destroy() {

    }
}
