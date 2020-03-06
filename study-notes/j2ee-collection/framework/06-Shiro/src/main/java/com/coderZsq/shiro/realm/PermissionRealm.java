package com.coderZsq.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class PermissionRealm extends AuthorizingRealm {
    @Override
    public String getName() {
        return "PermissionRealm";
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("AuthorizationInfo.doGetAuthorizationInfo");
        // 传递用户身份
        String principle = (String) principals.getPrimaryPrincipal();
        // 根据用户信息 -- 查询 所拥有的权限, 角色
        List<String> roles = Arrays.asList("role1", "role2");
        List<String> permissions = Arrays.asList("order:create", "order:audit", "order:delete");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(new HashSet<>(roles));
        authorizationInfo.setStringPermissions(new HashSet<>(permissions));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal(); // 获取身份信息
        String username = "zhangsan";
        String password = "666";
        if (!username.equals(principal)) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, password, getName());
        return authenticationInfo;
    }
}
