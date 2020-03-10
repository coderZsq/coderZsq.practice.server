package com.coderZsq.rbac.shiro;

import com.coderZsq.rbac.domain.Employee;
import com.coderZsq.rbac.mapper.EmployeeMapper;
import com.coderZsq.rbac.mapper.RoleMapper;
import com.coderZsq.rbac.mapper.PermissionMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public String getName() {
        return "UserRealm";
    }


    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = token.getPrincipal().toString();
        Employee employee = employeeMapper.queryByName(username);
        if(employee==null){
            return null;
        }
        return new SimpleAuthenticationInfo(employee,employee.getPassword(),getName());
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Employee employee = (Employee) principals.getPrimaryPrincipal();
        //判断是否是管理员
        if(employee.isAdmin()){
            info.addRole("ADMIN");
            info.addStringPermission("*:*");
        }else{
            List<String> roles = roleMapper.queryRoleSnsByEmpId(employee.getId());
            info.addRoles(roles);
            //查询权限
            List<String> permissions = permissionMapper.queryByEmployeeId(employee.getId());
            info.addStringPermissions(permissions);
        }
        return info;
    }


}
