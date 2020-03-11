package com.coderZsq.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

public class ShiroDemo {
    @Test
    public void testInit() throws Exception {
        // 1. 构建一个安全管理器对象
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        // 2. 获取到安全管理器 初始化 认证 授权, Realm, 加载配置文件
         SecurityManager securityManager =  factory.getInstance();
        // 3. 绑定到当前运行环境
        SecurityUtils.setSecurityManager(securityManager);
        // 4. 获取一个登录主体对象
        Subject subject = SecurityUtils.getSubject();
        // 5. 开始登录操作 创建一个token对象
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            if (e instanceof UnknownAccountException || e instanceof IncorrectCredentialsException) {
                System.out.println("用户名或密码错误");
            }
        }
        if (subject.isAuthenticated()) {
            System.out.println("用户登录成功");
        }
        // 6. 退出
        subject.logout();
    }

    @Test
    public void testRealm() throws Exception {
        // 1. 构建一个安全管理器对象
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        // 2. 获取到安全管理器 初始化 认证 授权, Realm, 加载配置文件
        SecurityManager securityManager =  factory.getInstance();
        // 3. 绑定到当前运行环境
        SecurityUtils.setSecurityManager(securityManager);
        // 4. 获取一个登录主体对象
        Subject subject = SecurityUtils.getSubject();
        // 5. 开始登录操作 创建一个token对象
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            if (e instanceof UnknownAccountException || e instanceof IncorrectCredentialsException) {
                System.out.println("用户名或密码错误");
            }
        }
        if (subject.isAuthenticated()) {
            System.out.println("用户登录成功");
        }
        // 6. 退出
        subject.logout();
    }

    @Test
    public void testPassword() throws Exception {
        // 1. 构建一个安全管理器对象
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-cryptography.ini");
        // 2. 获取到安全管理器 初始化 认证 授权, Realm, 加载配置文件
        SecurityManager securityManager =  factory.getInstance();
        // 3. 绑定到当前运行环境
        SecurityUtils.setSecurityManager(securityManager);
        // 4. 获取一个登录主体对象
        Subject subject = SecurityUtils.getSubject();
        // 5. 开始登录操作 创建一个token对象 md5 sha256 sha1
        // 对称加密 非对称加密 密码可以解密
        // 对称加密: 加密和解密用的秘钥一样
        // 非对称加密: 公钥 私钥 --> 公钥加密 --> 私钥解密; 私钥加密 --> 公钥解密 RSA git
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            if (e instanceof UnknownAccountException || e instanceof IncorrectCredentialsException) {
                System.out.println("用户名或密码错误");
            }
        }
        if (subject.isAuthenticated()) {
            System.out.println("用户登录成功");
        }
        // 6. 退出
        subject.logout();
    }

    @Test
    public void testRole() throws Exception {
        // 1. 构建一个安全管理器对象
        // IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-permission-realm.ini");

        // 2. 获取到安全管理器 初始化 认证 授权, Realm, 加载配置文件
        SecurityManager securityManager =  factory.getInstance();
        // 3. 绑定到当前运行环境
        SecurityUtils.setSecurityManager(securityManager);
        // 4. 获取一个登录主体对象
        Subject subject = SecurityUtils.getSubject();
        // 5. 开始登录操作 创建一个token对象 md5 sha256 sha1
        // 对称加密 非对称加密 密码可以解密
        // 对称加密: 加密和解密用的秘钥一样
        // 非对称加密: 公钥 私钥 --> 公钥加密 --> 私钥解密; 私钥加密 --> 公钥解密 RSA git
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            if (e instanceof UnknownAccountException || e instanceof IncorrectCredentialsException) {
                System.out.println("用户名或密码错误");
            }
        }
        if (subject.isAuthenticated()) {
            System.out.println("用户登录成功");
        }
        // 用户认证状态
        Boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("用户认证状态: " + isAuthenticated);
        // 是否有某一个角色
        System.out.println("用户是否拥有一个角色: " + subject.hasRole("role2"));
        // 是否有多个角色
        System.out.println("用户是否拥有多个角色: " + subject.hasAllRoles(Arrays.asList("role1", "role2")));
        // 角色检查, 如果没有就抛出异常
        // subject.checkRole("role1");
        // subject.checkRoles(Arrays.asList("role1", "role2"));
        // 6. 退出
        subject.logout();
    }

    @Test
    public void testPermission() throws Exception {
        // 1. 构建一个安全管理器对象
        // IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-permission-realm.ini");
        // 2. 获取到安全管理器 初始化 认证 授权, Realm, 加载配置文件
        SecurityManager securityManager =  factory.getInstance();
        // 3. 绑定到当前运行环境
        SecurityUtils.setSecurityManager(securityManager);
        // 4. 获取一个登录主体对象
        Subject subject = SecurityUtils.getSubject();
        // 5. 开始登录操作 创建一个token对象 md5 sha256 sha1
        // 对称加密 非对称加密 密码可以解密
        // 对称加密: 加密和解密用的秘钥一样
        // 非对称加密: 公钥 私钥 --> 公钥加密 --> 私钥解密; 私钥加密 --> 公钥解密 RSA git
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            if (e instanceof UnknownAccountException || e instanceof IncorrectCredentialsException) {
                System.out.println("用户名或密码错误");
            }
        }
        if (subject.isAuthenticated()) {
            System.out.println("用户登录成功");
        }
        // 用户认证状态
        Boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("用户认证状态: " + isAuthenticated);
        // 是否有某个权限
        System.out.println("用户是否拥有一个权限: " + subject.isPermitted("user:create"));
        // 是否有多个权限
        System.out.println("用户是否拥有多个权限: " + subject.isPermittedAll("user:create", "user:update", "user:insert"));
        // 权限检查, 如果没有就抛出异常
        // subject.checkPermission("user:delete");
        // subject.checkPermissions("user:create", "user:update");
        // 6. 退出
        subject.logout();
    }

    @Test
    public void testCrytography() throws Exception {
        // md5加密, 不加盐
        String password_md5 = new Md5Hash("666").toString();
        System.out.println("md5加密, 不加盐=" + password_md5);
        // md5加密, 加盐, 一次散列
        String passeord_md5_salt_1 = new Md5Hash("666", "abcd", 1).toString();
        System.out.println("password_md5_salt_1=" + passeord_md5_salt_1);
        // md5加密, 加盐, 再次散列
        String password_md5_salt_2 = new Md5Hash("666", "abcd", 2).toString();
        System.out.println("password_md5_salt_2=" + password_md5_salt_2);
    }
}
