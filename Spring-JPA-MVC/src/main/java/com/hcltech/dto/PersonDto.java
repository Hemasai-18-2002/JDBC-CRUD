package com.hcltech.dto;

import com.hcltech.model.Address;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.util.Set;


public class PersonDto {
    private Integer id;
    @NotNull(message = "First name should not be null")
    private String firstName;
    @Size(min = 2 ,max = 10,message = "Last name should contain 2 to 10 characters")
    private String lastName;
    private String fullName;

    private ProjectDto projectDto;
    private Set<AddressDto> addressDtos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ProjectDto getProjectDto() {
        return projectDto;
    }

    public void setProjectDto(ProjectDto projectDto) {
        this.projectDto = projectDto;
    }

    public Set<AddressDto> getAddressDtos() {
        return addressDtos;
    }

    public void setAddressDtos(Set<AddressDto> addressDtos) {
        this.addressDtos = addressDtos;
    }
}
