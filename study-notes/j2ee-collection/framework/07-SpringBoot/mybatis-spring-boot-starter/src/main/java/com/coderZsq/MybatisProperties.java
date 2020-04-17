package com.coderZsq;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@Data
@ConfigurationProperties(prefix = "mybatis")
public class MybatisProperties {
    private Resource[] mapperLocations;
    private String typeAliasesPackage;
}
