package com.hcltech.Student.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExamModelMapperConfig {

    @Bean
    public ModelMapper examModelMapper() {
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }
}