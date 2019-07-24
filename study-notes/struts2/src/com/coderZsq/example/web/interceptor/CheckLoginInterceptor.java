package com.coderZsq.example.web.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckLoginInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Object user = actionInvocation.getInvocationContext().getSession().get("user_in_session");
        if (user == null) {
            return Action.LOGIN;
        }
        return actionInvocation.invoke();
    }
}
