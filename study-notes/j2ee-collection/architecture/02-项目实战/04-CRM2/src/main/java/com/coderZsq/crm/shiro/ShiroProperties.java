package com.coderZsq.crm.shiro;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "shiro")
@Setter@Getter
public class ShiroProperties {
    private String hashAlgorithmName;
    private int hashIterations;
    private String cookieName;
    private String cookiePath;
    private String cookieDomain;
    private int cookieMaxAge;
    private String cacheManagerConfigFile;
    private String loginUrl;
}
