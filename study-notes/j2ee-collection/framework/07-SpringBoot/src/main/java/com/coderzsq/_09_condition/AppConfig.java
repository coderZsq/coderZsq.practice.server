package com.coderzsq._09_condition;
import com.coderzsq._03_bean.OtherBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppConfig {
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }

    @Bean
    @ConditionalOnMissingBean2
    public OtherBean otherBean1() {
        System.out.println("AppConfig.otherBean1");
        return new OtherBean();
    }

    @Bean
    @ConditionalOnMissingBean2
    public OtherBean otherBean2() {
        System.out.println("AppConfig.otherBean2");
        return new OtherBean();
    }
}
