package com.sq.jk.common.prop;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@ConfigurationProperties("jk")
@Component
@Data
public class JkProperties implements ApplicationContextAware {
    private Cfg cfg;
    private Upload upload;
    private static JkProperties properties;

    public static JkProperties get() {
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

    @Data
    public static class Upload {
        private String basePath;
        private String uploadPath;
        private String imagePath;
        private String videoPath;

        public String getImageRelativePath() {
            return uploadPath + imagePath;
        }

        public String getVideoRelativePath() {
            return uploadPath + videoPath;
        }
    }
}
