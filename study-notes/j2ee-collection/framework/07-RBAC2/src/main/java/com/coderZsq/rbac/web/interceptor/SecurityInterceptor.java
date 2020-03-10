package com.coderZsq.rbac.web.interceptor;

import com.coderZsq.rbac.domain.Employee;
import com.coderZsq.rbac.util.RequiredPermission;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SecurityInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Employee currentUser = (Employee) request.getSession().getAttribute("currentUser");
        //如果是管理员 直接放行
        if(currentUser.isAdmin()){
            return true;
        }
        //判断请求的是否是Controller的处理方法
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod  hm= (HandlerMethod) handler;
        RequiredPermission methodAnnotation = hm.getMethodAnnotation(RequiredPermission.class);
        if(methodAnnotation ==null){ //不需要权限校验
            return true;
        }
        String exp = methodAnnotation.value()[1];//获取到方法的的表达式
        //获取当前用户的权限表达式
        List<String> permissions = (List<String>) request.getSession().getAttribute("currentPermission");
        if(permissions.contains(exp)){
            return true;
        }else {
            request.getRequestDispatcher("/WEB-INF/views/common/nopermission.jsp").forward(request, response);
            return false;
        }
    }


}
