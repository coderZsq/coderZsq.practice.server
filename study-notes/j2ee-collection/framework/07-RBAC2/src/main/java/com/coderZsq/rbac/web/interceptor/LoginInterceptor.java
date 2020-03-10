package com.coderZsq.rbac.web.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor  extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Object currentUser = request.getSession().getAttribute("currentUser");
        if(currentUser==null){
            response.sendRedirect("/login.jsp");
            return false;
        }
        // 登录成功
        return true;//放行
    }


}
