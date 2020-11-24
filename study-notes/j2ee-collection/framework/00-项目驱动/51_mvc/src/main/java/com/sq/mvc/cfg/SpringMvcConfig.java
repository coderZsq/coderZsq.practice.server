package com.sq.mvc.cfg;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // registry.addResourceHandler("/abc1/**")
        //         .addResourceLocations("classpath:/static/");
        //
        // registry.addResourceHandler("/abc2/**")
        //         .addResourceLocations("file:///Users/zhushuangquan/Desktop/upload/");
    // }
}
