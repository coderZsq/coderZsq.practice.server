package com.coderZsq.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class PasswordRealm extends AuthorizingRealm {
    @Override
    public String getName() {
        return "MyRealm";
    }

    // 认证管理
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal(); // 获取身份信息
        String username = "zhangsan";
        String password = "1c44247e99da6dac31c3f3902fa9baf5";
        String salt = "abcd";
        if (!username.equals(principal)) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, password, ByteSource.Util.bytes(salt), getName());
        return authenticationInfo;
    }

    // 授权管理
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}
