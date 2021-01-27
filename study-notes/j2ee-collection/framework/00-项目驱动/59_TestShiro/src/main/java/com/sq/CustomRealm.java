package com.sq;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class CustomRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 当主体 (subject) 要进行认证时, 就会调用
     *
     * 开发者需要在这个方法中干啥? [一般]
     * 根据用户名查询用户的具体信息 (用户名, 密码)
     *
     * @param token subject.login()登录时传进来的token
     * @return 用户名的具体信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取token
        UsernamePasswordToken tk = (UsernamePasswordToken) token;

        // 根据用户名去数据库中查询用户信息
        String username = (String) tk.getPrincipal();
        SysUser user = Dbs.get(username);
        if (user == null) return null;

        // 判断认证是否成功
        // if (user == null) {
        //     throw new UnknownAccountException();
        // }

        // String password = (String) tk.getCredentials();
        // if (!password.equals(user.getPassword())) {
        //     throw new IncorrectCredentialsException();
        // }

        // 认证成功
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
    }
}
