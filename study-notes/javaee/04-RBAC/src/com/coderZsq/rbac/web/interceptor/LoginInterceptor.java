package com.coderZsq.rbac.web.interceptor;

import com.coderZsq.rbac.domain.Employee;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 去获取session信息即可, 如果有用户信息, 说明登录成功, 否则没有登录
        httpServletResponse.setContentType("text/json;charset=utf-8");
        HttpSession session = httpServletRequest.getSession();
        Employee employee = (Employee) session.getAttribute("user");
        if (employee == null) {
            return true;
        } else {
            // 提示用户没有登录
            httpServletResponse.setStatus(500001);
            httpServletResponse.getWriter().print("请用户先登录");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
