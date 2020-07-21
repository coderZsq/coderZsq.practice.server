package com.coderZsq.rbac.web.interceptor;

import com.coderZsq.rbac.domain.Employee;
import com.coderZsq.rbac.utils.RequiresPermission;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Set;

public class PermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("text/json;charset=UTF-8");
        //1 检查用户是否登录
        Cookie[] cookies = request.getCookies();
        System.out.println(Arrays.toString(cookies));
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if(user!=null && user instanceof Employee){
            Employee employee= (Employee) user;
            if(employee.isAdmin()){
                return true;
            }
            if(handler instanceof HandlerMethod){
                HandlerMethod hm= (HandlerMethod) handler;
                RequiresPermission requiresPermission = hm.getMethodAnnotation(RequiresPermission.class);
                if(requiresPermission==null){
                    return true;//不需要权限校验
                }else{
                    String expression = hm.getBean().getClass().getSimpleName()+":"+hm.getMethod().getName();
                    String name = requiresPermission.value();//获取到表达式
                    Set<String> expressions = (Set<String>) session.getAttribute("expressions");
                    if(expressions.contains(expression)){
                        return true;
                    }else{
                        response.setStatus(500002);
                        response.getWriter().print("您没有"+name+"操作的权限");
                        return false;
                    }
                }
            }
        }else{
            response.setStatus(500001);
            response.getWriter().print("请先登录系统");
            return false;
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
