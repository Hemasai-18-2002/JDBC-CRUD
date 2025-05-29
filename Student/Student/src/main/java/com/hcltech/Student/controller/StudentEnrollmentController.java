package com.hcltech.Student.controller;

import com.hcltech.Student.dto.StudentDto;
import com.hcltech.Student.model.Student;
import com.hcltech.Student.service.StudentExamService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentEnrollmentController {

    private final StudentExamService studentExamService;
    private final ModelMapper modelMapper;

    public StudentEnrollmentController(StudentExamService studentExamService, ModelMapper modelMapper) {
        this.studentExamService = studentExamService;
        this.modelMapper = modelMapper;
    }

    // Endpoint to link a student to an exam
    @PostMapping("/{studentId}/enroll-exam/{examId}")
    public ResponseEntity<StudentDto> enrollStudentInExam(
            @PathVariable Integer studentId,
            @PathVariable Integer examId) {

        // Call the service method to establish and persist the relationship
        Student updatedStudent = studentExamService.addExamToStudent(studentId, examId);

        // Return the updated student DTO
        return new ResponseEntity<>(modelMapper.map(updatedStudent, StudentDto.class), HttpStatus.OK);
    }
}