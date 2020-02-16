package com.coderZsq._28_filter;

import javax.servlet.*;
import java.io.IOException;

public class FilterDemo3 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("FilterDemo1.init()");
    }

    // 执行过滤
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FilterDemo3...before");
        filterChain.doFilter(servletRequest, servletResponse); // 放行操作
        System.out.println("FilterDemo3...after");
    }

    @Override
    public void destroy() {

    }
}
