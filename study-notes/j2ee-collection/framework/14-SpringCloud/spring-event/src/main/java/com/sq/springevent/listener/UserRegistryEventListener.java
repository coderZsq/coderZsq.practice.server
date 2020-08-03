package com.sq.springevent.listener;

import com.sq.springevent.event.UserRegistryEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

// 事件监听器
// @Component
public class UserRegistryEventListener implements ApplicationListener<UserRegistryEvent> {
    @Override
    public void onApplicationEvent(UserRegistryEvent event) {
        // 获取事件对应的
        System.out.println("用户信息: " + event.getUserBean());
        System.out.println("给指定用户发送短信");
        System.out.printf("%s\n", event.getSource());
    }
}
