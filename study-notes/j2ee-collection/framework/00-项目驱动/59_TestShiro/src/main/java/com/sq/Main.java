package com.sq;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * 认证流程
 * 1. Subject.login(token)
 * 2. SecurityManager -> Authenticator -> Realm [AuthorizingRealm]
 * 3. info = AuthorizingRealm.doGetAuthenticationInfo(token), 根据token信息查询对应的用户信息 (比如去数据库中查找)
 * 4. CredentialsMatcher.doCredentialsMatch(token, info), 判断token, info的Credentials是否匹配
 *
 * CredentialsMatcher: 专门用来判断Credentials是否正确
 *
 * 判断权限, 角色流程:
 * 1. Subject.isPermitted(permission), Subject.hasRole(role)
 * 2. SecurityManager -> Authorizer -> Realm [AuthorizingRealm]
 * 3. info = AuthorizingRealm.doGetAuthorizationInfo(principal的集合), 根据principal查询对应的角色, 权限信息
 * 4. 根据返回的info信息判断权限, 角色是否正确
 */
public class Main {
    public static void main(String[] args) {
        // 安全管理器: DefaultSecurityManager是SecurityManager的实现类型
        DefaultSecurityManager mgr = new DefaultSecurityManager();
        // 设置安全管理器
        SecurityUtils.setSecurityManager(mgr);
        // 设置Realm
        mgr.setRealm(new CustomRealm());

        // 主体: Subject
        Subject subject = SecurityUtils.getSubject();

        // 登录
        String username = "sq333";
        String password = "sq333";
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);

            System.out.println("[权限] user:read -> " + subject.isPermitted("user:read"));
            System.out.println("[权限] user:update -> " + subject.isPermitted("user:update"));
            System.out.println("[权限] user:delete -> " + subject.isPermitted("user:delete"));
            System.out.println("[权限] user:create -> " + subject.isPermitted("user:create"));
            System.out.println("[角色] admin -> " + subject.hasRole("admin"));
            System.out.println("[角色] normal -> " + subject.hasRole("normal"));


        } catch (UnknownAccountException e) {
            System.out.println("用户名不存在");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码不正确");
        } catch (AuthenticationException e) {
            System.out.println("认证失败");
        }
    }

    private static void testSimple() {
        // 安全管理器: DefaultSecurityManager是SecurityManager的实现类型
        DefaultSecurityManager mgr = new DefaultSecurityManager();
        // 设置安全管理器
        SecurityUtils.setSecurityManager(mgr);
        // 设置Realm
        mgr.setRealm(new IniRealm("classpath:realm.ini"));

        // 主体: Subject
        Subject subject = SecurityUtils.getSubject();

        // 登录
        String username = "sq";
        String password = "123456";
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            System.out.println("[登录之前] isAuthenticated -> " + subject.isAuthenticated());

            subject.login(token);

            System.out.println("[登录之后] isAuthenticated -> " + subject.isAuthenticated());

            System.out.println("[权限] user:read -> " + subject.isPermitted("user:read"));
            System.out.println("[权限] user:update -> " + subject.isPermitted("user:update"));
            System.out.println("[权限] user:delete -> " + subject.isPermitted("user:delete"));
            System.out.println("[权限] user:create -> " + subject.isPermitted("user:create"));
            System.out.println("[角色] admin -> " + subject.hasRole("admin"));
            System.out.println("[角色] normal -> " + subject.hasRole("normal"));

            // 退出登录
            subject.logout();
            System.out.println("[退出登录] isAuthenticated -> " + subject.isAuthenticated());
            System.out.println("[角色] normal -> " + subject.hasRole("normal"));

        } catch (UnknownAccountException e) {
            System.out.println("用户名不存在");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码不正确");
        } catch (AuthenticationException e) {
            System.out.println("认证失败");
        }
    }
}
