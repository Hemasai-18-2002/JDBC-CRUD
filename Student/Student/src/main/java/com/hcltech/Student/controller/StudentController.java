package com.hcltech.Student.controller;

import com.hcltech.Student.dto.StudentDto;
import com.hcltech.Student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studentservice/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController (StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDto> getAll(){
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDto getOneByID(@PathVariable("id") Integer id){
        return studentService.getOneByID(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //201
    public StudentDto create(@RequestBody StudentDto studentDto){
        return  studentService.create(studentDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)//200
    public StudentDto update(@RequestBody StudentDto studentDto){
        return studentService.update(studentDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id){
        studentService.delete(id);
    }
}