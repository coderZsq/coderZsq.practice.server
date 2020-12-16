package com.sq.jk.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("jk")
@Component
@Data
public class JkProperties {
    private Cfg cfg;

    @Data
    public static class Cfg {
        private String[] corsOrigins;
    }
}
