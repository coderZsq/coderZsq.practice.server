package cn.wolfcode.spring.test.aop.aspectj;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Leon
 */
public aspect UserAccessAspect {

    private List<Object> blackList = Collections.singletonList("leon");

    pointcut callLogin(String username, String password):
            call(void cn.wolfcode.spring.test.service.impl.UserServiceImpl.login(java.lang.String, java.lang.String)) && args(username, password);

    before(String username, String password): callLogin(username, password) {
        System.out.println("[UserAccessAspect] 当前登录的用户:" + username);
        System.out.println("[UserAccessAspect] 传入的用户名 & 密码: " + username + ", " + password);
    }

    Object around(String username, String password): callLogin(username, password) {
        if (blackList.contains(username)) {
            System.out.println("[UserAccessAspect] 你的账号已经被管理员封禁!");
            return false;
        }
        return proceed(username, password);
    }

    after(String username, String password): callLogin(username, password) {
        System.out.println("[UserAccessAspect] 用户登录成功：" + username);
    }
}
