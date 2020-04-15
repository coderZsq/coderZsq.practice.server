package com.coderzsq._04_mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ImportResource(locations = "classpath:applicationContext-tx.xml")
// @EnableTransactionManagement
// @MapperScan
public class AppConfig {
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }
    // 需要装配一个数据库连接池 druid对象
    // 手动装配
    // @Bean // 注入第三方的bean
    // @ConfigurationProperties(prefix = "db")
    // public DruidDataSource druidDataSource() {
    //     return new DruidDataSource();
    // }
}
