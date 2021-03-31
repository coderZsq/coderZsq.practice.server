package com.sq.imaginist.common.prop;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@ConfigurationProperties("imaginist")
@Component
@Data
public class ImaginistProperties implements ApplicationContextAware {
    private static ImaginistProperties properties;
    private Cfg cfg;

    public static ImaginistProperties get() {
        return properties;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        properties = this;
    }

    @Data
    public static class Cfg {
        private String[] corsOrigins;
    }
}
