package cn.wolfcode.netty.im.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Leon
 * @date 2021/4/28
 */
@Configuration
@MapperScan(value = {"cn.wolfcode.netty.im.webserver.base.mapper", "cn.wolfcode.netty.im.webserver.user.mapper"})
public class MyBatisConfig {
}
