package com.example.springbootjwtexample.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
    @Bean
    public OpenAPI springOpenApi() {
        final String securitySchemaName = "bearerAuth";
        return new OpenAPI()
                .info(new Info().title("Spring Boot Jwt Example")
                        .description("Sample JWT Auth Example with Spring Boot")
                        .version("v0.0.1")
                        .license(new License().name("MIT").url("http://github.com/semihcaliskan13")))
                .externalDocs(new ExternalDocumentation()
                        .description("Spring Boot JWT Example Documentation")
                        .url("http://github.com/semihcaliskan13"));
    }
}
