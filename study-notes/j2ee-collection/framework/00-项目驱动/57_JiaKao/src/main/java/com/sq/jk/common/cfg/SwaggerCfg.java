package com.sq.jk.common.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerCfg {
    @Bean
    public Docket docket(Environment environment) {
        boolean enable = environment.acceptsProfiles(Profiles.of("dev", "test"));
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("小码哥驾考系统接口文档")
                .description("这是一份比较详细的接口文档")
                .version("1.0.0")
                .build();
    }
    // @Bean
    // public Docket docket() {
    //     Docket docket = new Docket(DocumentationType.SWAGGER_2);
    //     docket.apiInfo(apiInfo());
    //     return docket;
    // }
    //
    // private ApiInfo apiInfo() {
    //     ApiInfoBuilder builder = new ApiInfoBuilder();
    //     builder.title("小码哥驾考系统接口文档");
    //     builder.description("这是一份比较详细的接口文档");
    //     builder.version("1.0.0");
    //     return builder.build();
    // }
}
