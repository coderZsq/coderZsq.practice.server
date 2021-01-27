package com.sq.jk.common.shiro;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroCfg {
    @Bean
    public Realm realm() {
        return new TokenRealm(new TokenMatcher());
    }

    /**
     * ShiroFilterFactoryBean 用来告诉Shiro如何进行拦截
     * 1. 拦截哪些URL
     * 2. 每个URL需要进行哪些filter
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(Realm realm) {
        ShiroFilterFactoryBean filterBean = new ShiroFilterFactoryBean();

        // 安全管理器
        filterBean.setSecurityManager(new DefaultWebSecurityManager(realm));

        // 添加一些自定义Filter
        Map<String, Filter> filters = new HashMap<>();
        filters.put("token", new TokenFilter());
        filterBean.setFilters(filters);

        // 设置URL如何拦截
        Map<String, String> urlMap = new LinkedHashMap<>();
        urlMap.put("/sysUsers/captcha", "anon");
        urlMap.put("/sysUsers/login", "anon");
        urlMap.put("/**", "token");

        filterBean.setFilterChainDefinitionMap(urlMap);

        return filterBean;
    }
}
