package com.coderZsq.filterchain;

import javax.servlet.*;
import java.io.IOException;

public class Filter3 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter3... before...");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Filter3... after...");
    }

    @Override
    public void destroy() {

    }
}
