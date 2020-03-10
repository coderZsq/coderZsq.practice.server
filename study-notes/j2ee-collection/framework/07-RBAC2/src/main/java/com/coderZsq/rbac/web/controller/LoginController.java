package com.coderZsq.rbac.web.controller;

import com.coderZsq.rbac.service.IEmployeeService;
import com.coderZsq.rbac.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IPermissionService permissionService;
    //认证失败的时候才会进入方法
    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        String errorMsg=null;
        //获取错误信息
        Object className = request.getAttribute("shiroLoginFailure");
        if(className!=null){
            errorMsg="用户名或者密码异常";
            request.setAttribute("errorMsg",errorMsg);
        }
        return "forward:/login.jsp";//成功
    }

}
