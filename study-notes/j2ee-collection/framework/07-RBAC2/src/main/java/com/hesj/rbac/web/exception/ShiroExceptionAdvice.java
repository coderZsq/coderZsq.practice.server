package com.hesj.rbac.web.exception;

import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice // 使用aop的功能 来处理统一异常 controller
public class ShiroExceptionAdvice {
    @ExceptionHandler(value= ShiroException.class)
    public String handlerShiroException(HttpServletRequest request) {
        // 转发到没有权限的页面
        return "forward:/WEB-INF/views/common/nopermission.jsp";
    }
}
