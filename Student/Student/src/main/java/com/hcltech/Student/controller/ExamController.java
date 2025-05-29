package com.hcltech.Student.controller; // Assuming controllers are in this package

import com.hcltech.Student.dto.ExamDto;
import com.hcltech.Student.service.ExamService;
import jakarta.validation.Valid; // For request body validation
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; // Using ResponseEntity for more control
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studentservice/v1/exams") // Consistent URL structure
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping
    public ResponseEntity<List<ExamDto>> getAllExams() {
        List<ExamDto> exams = examService.getAllExams();
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDto> getExamById(@PathVariable("id") Integer id) {
        ExamDto examDto = examService.getExamById(id);
        return new ResponseEntity<>(examDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExamDto> createExam(@Valid @RequestBody ExamDto examDto) {
        ExamDto createdExam = examService.createExam(examDto);
        return new ResponseEntity<>(createdExam, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ExamDto> updateExam(@Valid @RequestBody ExamDto examDto) {
        ExamDto updatedExam = examService.updateExam(examDto);
        return new ResponseEntity<>(updatedExam, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable("id") Integer id) {
        examService.deleteExam(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /*
    @PostMapping("/{examId}/students/{studentId}")
    public ResponseEntity<Void> addStudentToExam(@PathVariable Integer examId, @PathVariable Integer studentId) {
        examService.addStudentToExam(examId, studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{examId}/students/{studentId}")
    public ResponseEntity<Void> removeStudentFromExam(@PathVariable Integer examId, @PathVariable Integer studentId) {
        examService.removeStudentFromExam(examId, studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    */
}