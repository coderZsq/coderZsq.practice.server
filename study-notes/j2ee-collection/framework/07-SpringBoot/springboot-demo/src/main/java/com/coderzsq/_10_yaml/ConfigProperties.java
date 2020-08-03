package com.coderzsq._10_yaml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "sq")
@Setter@Getter@ToString
public class ConfigProperties {
    private String name;
    private String[] scores;
    private Map<String, String> info;
}
