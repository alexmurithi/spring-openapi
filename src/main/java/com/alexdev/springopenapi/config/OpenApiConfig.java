package com.alexdev.springopenapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("User Management API")
                                .description("User Management Application"));
    }
}
