package com.hcltech.Student.dto;

public class StudentDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String course;
    private String fullName;


    public StudentDto() {}


    public StudentDto(String firstName, String lastName, String course) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
    }

    public StudentDto(Integer id, String firstName, String lastName, String course, String fullName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
        this.fullName = fullName;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }


    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
}