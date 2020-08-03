package com.sq.springevent.web;

import com.sq.springevent.domain.UserBean;
import com.sq.springevent.event.UserRegistryEvent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements ApplicationContextAware, ApplicationEventPublisherAware, BeanFactoryAware {
    // @Autowired
    private ApplicationEventPublisher publisher;

    private BeanFactory beanFactory;

    private ApplicationContext context;

    @RequestMapping("/registry")
    public String registry(UserBean userBean) {
        System.out.println("调用后台业务方法, 注册用户成功");
        // 发布事件
        // publisher.publishEvent(new UserRegistryEvent(this, userBean));
        // 直接发布一个对象
        publisher.publishEvent(userBean);
        return "success";
    }

    @EventListener
    public void onEvent(UserRegistryEvent event) { // UserRegistryEvent 事件
        // 获取事件对应的
        System.out.println("用户信息: " + event.getUserBean());
        System.out.println("给指定用户发送短信");
        System.out.printf("%s", event.getSource());
    }

    @EventListener
    public void onEvent(PayloadApplicationEvent<UserBean> event) { // UserRegistryEvent 事件
        // 获取事件对应的
        System.out.println("用户信息: " + event.getPayload());
        System.out.println("给指定用户发送短信");
        System.out.printf("%s", event);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
