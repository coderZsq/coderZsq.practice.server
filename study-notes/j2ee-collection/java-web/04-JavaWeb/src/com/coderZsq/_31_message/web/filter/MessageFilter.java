package com.coderZsq._31_message.web.filter;

import com.coderZsq._31_message.web.request.MessageRequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// 敏感字过滤器
@WebFilter("/*")
public class MessageFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 把不具有处理敏感字的请求对象换成可以处理敏感字的请求对象
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletRequest requestWrapper = new MessageRequestWrapper(req);
        filterChain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
