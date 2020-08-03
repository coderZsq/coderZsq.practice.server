package com.sq.resume.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// @WebFilter(value = "/*", dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String uri = request.getRequestURI();
        // 优先放开的请求
        if (uri.contains("/asset/")
                || uri.contains("/contact/save")) {
            chain.doFilter(request, response);
        } else if (uri.contains("/admin")
                || uri.contains("/save")
                || uri.contains("/remove")
                || uri.contains("/user/password")
                || uri.contains("/user/updatePassword")) {
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

    /**
     * 当项目部署到Web容器的时候调用 (当Filter被加载到Web容器中)
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        // 适合做一些资源的一次性加载, 初始化
        System.out.println("LoginFilter.init");
    }

    /**
     * 当项目从Web容器中取消部署时调用 (当Filter从Web容器中移除时调用)
     */
    @Override
    public void destroy() {
        // 适合做一些资源回收操作
        System.out.println("LoginFilter.destroy");
    }
}
