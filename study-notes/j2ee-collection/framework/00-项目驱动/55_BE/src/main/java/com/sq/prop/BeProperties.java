package com.sq.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("be")
@Component
public class BeProperties {
    private String[] corsOrigins;

    public String[] getCorsOrigins() {
        return corsOrigins;
    }

    public void setCorsOrigins(String[] corsOrigins) {
        this.corsOrigins = corsOrigins;
    }
}
