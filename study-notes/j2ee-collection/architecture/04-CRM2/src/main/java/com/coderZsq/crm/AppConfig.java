package com.coderZsq.crm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.DelegatingFilterProxy;

@SpringBootApplication
@MapperScan("com.coderZsq.crm.mapper")
// @ServletComponentScan("com.coderZsq.crm.web.filter")
public class AppConfig {
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }

    // 注册第三方filter servlet listener
    @Bean
    public FilterRegistrationBean shiroFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy filterProxy = new DelegatingFilterProxy();
        filterProxy.setTargetFilterLifecycle(true);
        registrationBean.setFilter(filterProxy);
        registrationBean.setName("shiroFilter");
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
