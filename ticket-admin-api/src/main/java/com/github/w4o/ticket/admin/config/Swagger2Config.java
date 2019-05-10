package com.github.w4o.ticket.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author frank
 * @date 2019-05-09 11:29
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 是否开启swagger
     * 正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
     */
    @Value("${ticket.swagger.enabled}")
    Boolean swaggerEnabled = false;

    @Value("${ticket.version}")
    String version;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled).select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("com.github.w4o.ticket.*.controller"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any()).build().pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Ticket REST Api")
                .description("Ticket REST Api")
                .version(version)
                .build();
    }
}
