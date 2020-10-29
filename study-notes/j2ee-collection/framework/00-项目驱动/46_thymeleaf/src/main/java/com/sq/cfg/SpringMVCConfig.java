package com.sq.cfg;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMVCConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 首页的映射
        registry.addViewController("/").setViewName("login");

        // 其他页面的映射
        registry.addViewController("/comment").setViewName("02_comment");
        // registry.addViewController("/comment/").setViewName("02_comment");

        registry.addViewController("/i18n").setViewName("05_i18n");
        // registry.addViewController("/i18n/").setViewName("05_i18n");
    }
}
