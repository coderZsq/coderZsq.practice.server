package com.sq.mvc.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("project")
@Component
@Data
public class ProjectProperties {
    private Upload upload;

    @Data
    public static class Upload {
        private String basePath;
        private String imagePath;
        private String videoPath;

        public String getImageFullpath() {
            return basePath + imagePath;
        }

        public String getVideoFullpath() {
            return basePath + videoPath;
        }
    }
}
