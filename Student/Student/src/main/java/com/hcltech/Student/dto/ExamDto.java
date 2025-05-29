package com.hcltech.Student.dto; // Assuming DTOs are in this package

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

public class ExamDto {
    private Integer id;

    @NotBlank(message = "Exam name cannot be blank")
    @Size(max = 100, message = "Exam name cannot exceed 100 characters")
    private String name;

    @NotNull(message = "Exam date cannot be null")
    private LocalDate examDate;


    public ExamDto() {
    }

    public ExamDto(Integer id, String name, LocalDate examDate) {
        this.id = id;
        this.name = name;
        this.examDate = examDate;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    @Override
    public String toString() {
        return "ExamDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", examDate=" + examDate +
                '}';
    }
}