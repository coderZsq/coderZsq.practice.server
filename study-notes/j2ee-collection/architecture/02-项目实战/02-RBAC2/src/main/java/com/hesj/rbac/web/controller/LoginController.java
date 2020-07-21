package com.hesj.rbac.web.controller;

import com.hesj.rbac.service.IEmployeeService;
import com.hesj.rbac.service.IPermissionService;
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
    // 认证失败的时候才会进入方法
    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        String errorMsg = null;
        // 获取错误信息
        Object className = request.getAttribute("shiroLoginFailure");
        if (className != null) {
            errorMsg = "用户名或者密码异常";
            request.setAttribute("errorMsg", errorMsg);
        }
        return "forward:/login.jsp"; // 成功

        // HttpSession session = request.getSession();
        // Employee employee = employeeService.selectByNameAndPassword(username, password);
        // if (employee == null) { // 用户名或者密码不正确
        //     return "redirect:/login.jsp"; // 成功
        // } else  { // 登录成功
        //     // 把用户信息放到session中
        //     session.setAttribute("currentUser", employee);
        //     // 把用户拥有的权限列表放到session中
        //     List<String> permissions = permissionService.selectAllExpressionsByEmployeeId(employee.getId());
        //     session.setAttribute("currentPermission", permissions);
        //     return "redirect:/department/list.do";//成功
        // }
    }

}
