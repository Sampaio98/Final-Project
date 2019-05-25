package com.project.finalproject.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.finalproject.model.Admin;
import com.project.finalproject.model.Professional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(Professional.class);
                objectMapper.registerSubtypes(Admin.class);
                super.configure(objectMapper);
            }
        };
        return builder;
    }
}
