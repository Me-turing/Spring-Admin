package com.ocbc.les.frame.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger 配置
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .components(new Components()
                        .addSecuritySchemes("Bearer",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)
                                        .name("Authorization")))
                .addSecurityItem(new SecurityRequirement().addList("Bearer"));
    }

    private Info apiInfo() {
        return new Info()
                .title("OCBC LES 风险管理系统 API")
                .description("OCBC 大额风险管理系统接口文档")
                .version("1.0.0")
                .contact(new Contact()
                        .name("OCBC技术团队")
                        .email("tech@ocbc.com")
                        .url("https://www.ocbc.com"))
                .license(new License()
                        .name("Private License")
                        .url("https://www.ocbc.com/license"));
    }
} 