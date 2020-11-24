package com.sq.cfg;

import com.sq.converter.DateConverter;
import com.sq.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.StandardCharsets;
import java.util.List;

@ComponentScan({"com.sq.controller", "com.sq.interceptor"})
@EnableWebMvc
public class SpringMVCConfig implements WebMvcConfigurer {
    @Autowired
    private MyInterceptor myInterceptor;
    @Autowired
    private DateConverter dateConverter;

    // @Bean
    // public MyInterceptor myInterceptor() {
    //     return new MyInterceptor();
    // }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/page/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/asset/**");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(dateConverter);
    }

    /**
     * 相当于<mvc:default-servlet-handler/>
     * 用以处理静态资源
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 设置返回的普通字符串的编码
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setDefaultCharset(StandardCharsets.UTF_8);
        converters.add(stringConverter);

        // 设置返回的JSON数据的编码
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setDefaultCharset(StandardCharsets.UTF_8);
        converters.add(jsonConverter);
    }
}
