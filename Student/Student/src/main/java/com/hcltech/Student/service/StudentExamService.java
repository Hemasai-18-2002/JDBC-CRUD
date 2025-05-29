package com.hcltech.Student.service;

import com.hcltech.Student.model.Exam;
import com.hcltech.Student.model.Student;
import com.hcltech.Student.repository.ExamRepository;
import com.hcltech.Student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentExamService {

    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;

    public StudentExamService(StudentRepository studentRepository, ExamRepository examRepository) {
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
    }

    @Transactional // Ensure this method is transactional
    public Student addExamToStudent(Integer studentId, Integer examId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + examId));

        // *** Use the helper method to maintain bidirectional consistency ***
        student.addExam(exam);

        // Save the owning side (Student) to persist the relationship
        return studentRepository.save(student);
    }

    // You might also have a removeExamFromStudent method here
    @Transactional
    public Student removeExamFromStudent(Integer studentId, Integer examId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Exam exam = examRepository.findById(examId).orElseThrow();
        student.removeExam(exam);
        return studentRepository.save(student);
    }
}