package com.data.student.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Student API Services",
                description = "Student API Documentation",
                version = "1.0",
                termsOfService = "company url !!"
        )
)
public class OpenApiConfig {
}
