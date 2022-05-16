package com.telegaming.helpi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfiguration {
    @Bean(name = "helpi")
    public OpenAPI helpi()
    {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Helpi REST api")
                        .description("Helpi API implemented with Spring Boot RESTFul service and documented using springdoc-openapi and OpenAPI 3.0"));
    }
}
