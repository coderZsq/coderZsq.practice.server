package com.sq.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class Filter1 implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 请求之前的处理
        System.out.println("Filter1 - doFilter - 1");
        chain.doFilter(req, resp);
        // 服务器响应后的处理
        System.out.println("Filter1 - doFilter - 2");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
