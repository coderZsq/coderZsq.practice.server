package com.coderZsq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// context: 环境的意思, 是Action的环境
// 封装了请求和响应对象
public class ActionContext {
    private HttpServletRequest request;
    private HttpServletResponse response;

    // 为每一个线程都分配了一个ActionContext对象的副本
    private static ThreadLocal<ActionContext> threadLocal = new ThreadLocal<>();

    public static ActionContext getContext() {
        return threadLocal.get();
    }

    public static void setContext(ActionContext context) {
        threadLocal.set(context);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ActionContext(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
}
