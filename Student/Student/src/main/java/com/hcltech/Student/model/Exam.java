package com.hcltech.Student.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "exam_date")
    private LocalDate examDate;

    // Many-to-Many relationship with Student
    @ManyToMany(mappedBy = "exams")
    private Set<Student> students = new HashSet<>();

    public Exam() {
    }

    public Exam(String name, LocalDate examDate) {
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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", examDate=" + examDate +
                '}';
    }

    // Optional: equals and hashCode for Set operations if needed
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return id != null && id.equals(exam.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}