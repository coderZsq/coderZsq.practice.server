package com.coderZsq.crm.shiro;

import com.coderZsq.crm.domain.Employee;
import com.coderZsq.crm.mapper.EmployeeMapper;
import com.coderZsq.crm.mapper.PermissionMapper;
import com.coderZsq.crm.mapper.RoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
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

    @Autowired
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        super.setCredentialsMatcher(credentialsMatcher);
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Employee employee = (Employee) principalCollection.getPrimaryPrincipal();
        //判断是否是管理员
        if (employee.isAdmin()) {
            info.addRole("admin");
            info.addStringPermission("*:*");//所有资源的所有权限, 中间使用:隔开
        } else {
            //根据用户查询权限和角色: 只查询编码
            List<String> roles = roleMapper.querySnByEmpId(employee.getId());
            //查询权限
            List<String> permissions = permissionMapper.querySnByEmpId(employee.getId());
            info.addRoles(roles);//添加角色
            info.addStringPermissions(permissions);//添加权限
        }
        return info;
    }

    //登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取登录的用户名
        String username = authenticationToken.getPrincipal().toString();
        log.info("username:[{}]", username);
        //根据用户名从数据库查询
        Employee employee = employeeMapper.queryByUsername(username);
        if (employee == null) {//不存在
            return null;
        }
        // 参数1: 身份主题, 参数2:密码, 参数3: 名字
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(employee, employee.getPassword(), getName());
        return info;
    }
}
