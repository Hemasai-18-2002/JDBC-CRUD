package com.hcltech.Service;

import com.hcltech.dto.PersonDto;
import com.hcltech.dto.ProjectDto;
import com.hcltech.model.Person;
import com.hcltech.model.Project;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private  ModelMapper modelMapper;
    public ProjectService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProjectDto toDto(Project project){
        if(project==null) return null;
            return modelMapper.map(project, ProjectDto.class);

    }
    private Project toEntity(ProjectDto projectDto) {
        if(projectDto ==null) return null;

        return modelMapper.map(projectDto, Project.class);
    }


}
