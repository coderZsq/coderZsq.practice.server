package com.coderZsq.crm.shiro;

import com.jagregory.shiro.freemarker.ShiroTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;

@Component
public class ShiroTag {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @PostConstruct // 初始化方法
    public void initShiroTagConfig() {
        System.out.println("ShiroTag.initShiroTagConfig");
        freeMarkerConfigurer.getConfiguration().setSharedVariable("shiro", new ShiroTags());
    }
}
