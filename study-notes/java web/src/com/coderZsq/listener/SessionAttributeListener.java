package com.coderZsq.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("属性添加" + httpSessionBindingEvent.getName() + ", " + httpSessionBindingEvent .getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("属性删除" + httpSessionBindingEvent.getName() + ", " + httpSessionBindingEvent .getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("属性替换" + httpSessionBindingEvent.getName() + ", " + httpSessionBindingEvent .getValue());
    }
}
