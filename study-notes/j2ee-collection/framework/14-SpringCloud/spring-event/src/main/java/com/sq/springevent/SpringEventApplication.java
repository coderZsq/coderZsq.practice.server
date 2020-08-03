package com.sq.springevent;

import com.sq.springevent.listener.UserRegistryEventListener;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringEventApplication {

    public static void main(String[] args) {
        // 启动方式一
        // SpringApplication.run(SpringEventApplication.class, args);
        // 通过构建器的方式启动
        new SpringApplicationBuilder(SpringEventApplication.class)
                .web(WebApplicationType.SERVLET)
                // .listeners(new UserRegistryEventListener()) // 可插拔的方式添加监听器
                .run(args);
    }

}
