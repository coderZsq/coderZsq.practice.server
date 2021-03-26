package com.sq.recorder.common.prop;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@ConfigurationProperties("recorder")
@Component
@Data
public class RecorderProperties implements ApplicationContextAware {
    private static RecorderProperties properties;
    private Cfg cfg;

    public static RecorderProperties get() {
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
