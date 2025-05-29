package com.hcltech.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean // This method will produce a Spring bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
