package com.sq.jk.common.cfg;

import com.sq.jk.prop.JkProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCfg implements WebMvcConfigurer {
    @Autowired
    private JkProperties properties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // /**表示对所有的路径开放全局跨域访问权限
        registry.addMapping("/**")
                // 开放哪些IP, 端口, 域名访问权限
                .allowedOrigins(properties.getCfg().getCorsOrigins())
                // 是否允许发送Cookie信息
                .allowCredentials(true)
                // 哪些HTTP方法允许跨域访问
                .allowedMethods("GET", "POST");
                // 允许HTTP请求中的携带哪些Header信息
    }
}
