package com.sq.cfg;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer2 { // implements WebApplicationInitializer {
    // @Override
    // public void onStartup(ServletContext servletContext) throws ServletException {
    //     // 父容器配置
    //     AnnotationConfigWebApplicationContext springCtx = new AnnotationConfigWebApplicationContext();
    //     springCtx.register(SpringConfig.class);
    //     // 通过监听器加载配置信息
    //     servletContext.addListener(new ContextLoaderListener(springCtx));
    //
    //     // 子容器配置
    //     AnnotationConfigWebApplicationContext mvcCtx = new AnnotationConfigWebApplicationContext();
    //     mvcCtx.register(SpringMVCConfig.class);
    //
    //     ServletRegistration.Dynamic servlet = servletContext.addServlet(
    //             "DispatcherServlet",
    //             new DispatcherServlet(mvcCtx));
    //     servlet.setLoadOnStartup(0);
    //     servlet.addMapping("/");
    //
    //     // filter
    //     FilterRegistration.Dynamic encodingFilter = servletContext.addFilter(
    //             "CharacterEncodingFilter",
    //             new CharacterEncodingFilter("UTF-8"));
    //     encodingFilter.addMappingForUrlPatterns(null, false, "/*");
    // }
}
