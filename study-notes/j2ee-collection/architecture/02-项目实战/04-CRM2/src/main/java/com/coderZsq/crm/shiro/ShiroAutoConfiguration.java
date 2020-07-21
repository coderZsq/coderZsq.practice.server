package com.coderZsq.crm.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;

@Configuration
@EnableConfigurationProperties(ShiroProperties.class)
public class ShiroAutoConfiguration {
    @Autowired
    private ShiroProperties properties;

    @Bean
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(properties.getHashAlgorithmName());
        matcher.setHashIterations(properties.getHashIterations());
        return matcher;
    }

    @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie cookie = new SimpleCookie();
        cookie.setName(properties.getCookieName());
        cookie.setPath(properties.getCookiePath());
        cookie.setDomain(properties.getCookieDomain());
        cookie.setMaxAge(properties.getCookieMaxAge());
        return cookie;
    }

    @Bean
    public RememberMeManager rememberMeManager(Cookie simpleCookie) {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCookie(simpleCookie);
        return manager;
    }

    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager manager = new EhCacheManager();
        manager.setCacheManagerConfigFile(properties.getCacheManagerConfigFile());
        return manager;
    }

    @Bean
    public SecurityManager securityManager(Realm userRealm, RememberMeManager rememberMeManager, CacheManager cacheManager) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userRealm);
        manager.setCacheManager(cacheManager);
        manager.setRememberMeManager(rememberMeManager);
        return manager;
    }

    @Bean
    public AbstractShiroFilter shiroFilter(SecurityManager securityManager) throws Exception {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl(properties.getLoginUrl());
        factoryBean.getFilters().put("crmAuthc", new CRMFormAuthenticationFilter());
        HashMap<String, String> map = new HashMap<>();
        map.put("/css/**", "anon");
        map.put("/js/**", "anon");
        map.put("/index.html", "anon");
        map.put("logout.do", "logout");
        map.put("/department/list.do", "user");
        map.put("/**", "crmAuthc");
        factoryBean.setFilterChainDefinitionMap(map);
        return (AbstractShiroFilter) factoryBean.getObject();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        return advisor;
    }
}
