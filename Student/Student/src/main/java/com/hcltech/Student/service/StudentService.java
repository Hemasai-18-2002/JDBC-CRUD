package com.hcltech.Student.service;

import com.hcltech.Student.dao.service.StudentDaoService;
import com.hcltech.Student.dto.StudentDto;
import com.hcltech.Student.model.Student;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private  StudentDaoService studentDaoService;
    private  ModelMapper modelMapper;
    public StudentService(StudentDaoService studentDaoService,ModelMapper modelMapper) {
        this.studentDaoService = studentDaoService;
        this.modelMapper=modelMapper;
        logger.info("StudentService initialized with StudentDaoService and ModelMapper.");
    }

    public List<StudentDto> getAll(){
        logger.info("Attempting to retrieve all students.");
        List<Student> allStudents = studentDaoService.getAll();
        logger.debug("Found {} students from DAO.", allStudents.size());
        List<StudentDto> result = toDto(allStudents);
        logger.info("Successfully retrieved and mapped all students.");
        return result;
    }
    public StudentDto getOneByID( int id){
        logger.info("Attempting to retrieve student with ID: {}", id);
        Student oneStudent = studentDaoService.getOneByID(id);
        StudentDto result = toDto(oneStudent);
        logger.info("Successfully retrieved and mapped student with ID: {}", id);
        return result;

    }

    public StudentDto create(StudentDto studentDto){
        logger.info("Attempting to create a new student: {}", studentDto.getFirstName());
        final Student student=toEntity(studentDto);
        logger.debug("Converted StudentDto to entity: {}", student.toString());
        Student savedStudent = studentDaoService.create(student);
        logger.info("Student saved to database with ID: {}", savedStudent.getId());
        StudentDto result = toDto(savedStudent);
        logger.info("Successfully created and mapped new student (ID: {}).", result.getId());
        return result;
    }


    public StudentDto update( StudentDto studentDto){
        logger.info("Attempting to update student with ID: {}", studentDto.getId());
        final Student student=toEntity(studentDto);
        logger.debug("Converted StudentDto to entity for update: {}", student.toString());
        Student savedStudent = studentDaoService.update(student);
        logger.info("Student with ID: {} updated successfully.", savedStudent.getId());
        StudentDto result = toDto(savedStudent);
        logger.info("Successfully updated and mapped student (ID: {}).", result.getId());
        return result;
    }

    public void delete(int id){
        logger.info("Attempting to delete student with ID: {}", id);
        studentDaoService.delete(id);
        logger.info("Student with ID: {} deleted successfully.", id);
    }

    //---------------------------------------------------------------

    private List<StudentDto> toDto(List<Student> students){
        logger.debug("Mapping list of Students to StudentDto list.");
        return students.stream()
                .map(student -> toDto(student))
                .collect(Collectors.toList());
    }

    private StudentDto toDto(Student student){
        logger.debug("Mapping Student entity (ID: {}) to StudentDto.", student != null ? student.getId() : "null");
        return modelMapper.map(student,StudentDto.class);

    }
//    private Student toEntity(StudentDto studentDto){
//
//        return modelMapper.map(studentDto,Student.class);
//    }
private Student toEntity(StudentDto studentDto){
    logger.debug("Mapping StudentDto (firstName: {}) to Student entity.", studentDto != null ? studentDto.getFirstName() : "null");
    return modelMapper.map(studentDto,Student.class);
}

}

