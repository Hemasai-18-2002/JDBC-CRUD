package com.hcltech.Student.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students") // Added table name for clarity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20, nullable = false, name = "first_name")
    private String firstName;

    @Column(length = 20, nullable = false, name = "last_name")
    private String lastName;

    @Column(length = 20, nullable = false)
    private String course;

    // Many-to-Many relationship with Exam
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "student_exam", // Name of the join table
            joinColumns = @JoinColumn(name = "student_id"), // Column in the join table that refers to Student
            inverseJoinColumns = @JoinColumn(name = "exam_id") // Column in the join table that refers to Exam
    )
    private Set<Exam> exams = new HashSet<>(); // Initialize to prevent NullPointerException

    // Constructors
    public Student() {
    }

    public Student(String firstName, String lastName, String course) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Set<Exam> getExams() {
        return exams;
    }

    public void setExams(Set<Exam> exams) {
        this.exams = exams;
    }


    public void addExam(Exam exam) {
        this.exams.add(exam);
        exam.getStudents().add(this);
    }

    public void removeExam(Exam exam) {
        this.exams.remove(exam);
        exam.getStudents().remove(this);
    }

    @Override
    public String toString() {
        return String.format("Student[id=%s, FirstName=%s, LastName=%s, Course=%s]", id, firstName, lastName, course);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id != null && id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}