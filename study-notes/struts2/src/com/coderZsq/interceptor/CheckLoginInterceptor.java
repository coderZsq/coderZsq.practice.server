package com.coderZsq.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Arrays;
import java.util.Map;

// 登录检查拦截器
public class CheckLoginInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 1L;
    // 需要放行的action名称
    private String[] unCheckedActionNames = {};

    // 对应着 <param name="actionNames">login,logon</param>
    public void setActionNames(String actionNames) {
        System.out.println("---" + actionNames);
        if (actionNames != null) {
            unCheckedActionNames = actionNames.split(",");
        }
    }

    // 执行拦截操作
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        System.out.println("登录检查拦截器...");

        // 如果当前action的名称为login, 也应该放行
        String actionName = actionInvocation.getProxy().getActionName();
        System.out.println(actionName);
        if (Arrays.asList(unCheckedActionNames).contains(actionName)) {
            return actionInvocation.invoke();
        }

        // 判断session中是否有USER_IN_SESSION
        // 如果有: 已经登录, 则放行
        // 如没有: 没有登录, 重新回到登录页面
        Map<String, Object> sessionMap = actionInvocation.getInvocationContext().getSession();
        Object user = sessionMap.get("USER_IN_SESSION");
        if (user == null) {
            return Action.LOGIN; // 返回login逻辑视图, 重定向到login.jsp页面
        }
        return actionInvocation.invoke(); // 放行去执行Action
    }
}
