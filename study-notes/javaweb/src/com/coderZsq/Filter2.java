package com.coderZsq;

import javax.servlet.*;
import java.io.IOException;

public class Filter2 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter2... before...");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Filter2... after...");
    }

    @Override
    public void destroy() {

    }
}
