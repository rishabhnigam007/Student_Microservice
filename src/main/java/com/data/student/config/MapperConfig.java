package com.data.student.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for application-wide object mapping.
 * Defines and exposes a ModelMapper bean used to map between entities and Data Transfer Objects (DTOs).
 */
@Configuration
public class MapperConfig {

    /**
     * Creates and configures a ModelMapper bean.
     *
     * @return a ModelMapper instance for object mapping
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
