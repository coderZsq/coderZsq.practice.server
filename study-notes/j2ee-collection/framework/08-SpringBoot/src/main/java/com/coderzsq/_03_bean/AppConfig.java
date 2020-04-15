package com.coderzsq._03_bean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppConfig {
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }
    @Bean
    public OtherBean otherBean() {
        return new OtherBean();
    }

    /**
     * 版本调整过
     * 以前: 方法名称和形式参数名称必须一样(从容器中根据Bean的名字和类型去获取bean)
     * 现在: 方法名称和形式参数名称可以不一样(从容器中根据Bean类型去获取bean)
     * @param otherBean
     * @return
     */
    @Bean
    public SomeBean someBean(OtherBean otherBean) {
        SomeBean someBean = new SomeBean();
        someBean.setOtherBean(otherBean);
        return someBean;
    }
}
