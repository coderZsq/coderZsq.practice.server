package com.coderZsq.rbac.web.controller;

import com.coderZsq.rbac.domain.Employee;
import com.coderZsq.rbac.domain.Permission;
import com.coderZsq.rbac.domain.Role;
import com.coderZsq.rbac.service.IEmployeeService;
import com.coderZsq.rbac.service.IPermissionService;
import com.coderZsq.rbac.service.IRoleService;
import com.coderZsq.rbac.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("login")
    public PageResult<Boolean> login(String username, String password, HttpServletRequest request){
        Employee user = employeeService.login(username, password);
        List<Role> roles = roleService.queryByEmpId(user.getId());
        Set<String> expressions=new HashSet<>();
        for (Role role : roles) {
            List<Permission> permissions = permissionService.queryByRoleId(role.getId());
            for (Permission permission : permissions) {
                expressions.add(permission.getExpression());
            }
        }
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("expressions", expressions);
        return PageResult.success(true);
    }
}
