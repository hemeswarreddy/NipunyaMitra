package com.sih.nm.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {

        return new OpenAPI()
                .info(new Info()
                        .title("Capacity Connect")
                        .version("1.0")
                        .description("Digital Capacity Building and Learning Management Portal"));
    }
}