package com.hcltech.Student.config;

import com.hcltech.Student.dto.StudentDto;
import com.hcltech.Student.model.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();


        Converter<Student, String> fullNameConverter = new Converter<Student, String>() {
            @Override
            public String convert(MappingContext<Student, String> context) {
                Student source = context.getSource();
                if (source == null) {
                    return null;
                }
                String firstName = source.getFirstName();
                String lastName = source.getLastName();

                if (firstName != null && lastName != null) {
                    return firstName + " " + lastName;
                } else if (firstName != null) {
                    return firstName;
                } else if (lastName != null) {
                    return lastName;
                } else {
                    return null;
                }
            }
        };



        mapper.typeMap(Student.class, StudentDto.class)
                .addMappings(m -> m.using(fullNameConverter).map(src -> src, StudentDto::setFullName));

        return mapper;
    }
}