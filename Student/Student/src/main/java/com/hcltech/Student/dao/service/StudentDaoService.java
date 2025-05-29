package com.hcltech.Student.dao.service;

import com.hcltech.Student.model.Student;
import com.hcltech.Student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StudentDaoService {

//    private List<Student> students=new ArrayList<>(Arrays.asList(new Student(null,"Hema","Java"),new Student(null,"Mouni","Python")));
    private StudentRepository studentRepository;

    public StudentDaoService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAll(){
        return studentRepository.findAll();
//        return persons;
    }

    public Student getOneByID(int id){
        return studentRepository.findById(id).orElse(null);
//        return persons.stream().filter(p-> p.getId().equals(id)).findFirst().orElse(null);
    }


    public Student create(Student student) {
        return studentRepository.save(student);
    }
    public Student update( Student student){
            Optional<Student> existingStudentOptional = studentRepository.findById(student.getId());
            if (existingStudentOptional.isPresent()) {
                Student existingStudent = existingStudentOptional.get();

                existingStudent.setFirstName(student.getFirstName());
                existingStudent.setLastName(student.getLastName());
                existingStudent.setCourse(student.getCourse());

                return studentRepository.save(existingStudent);
            } else {

                return null;
            }
        }
        public void delete(int id){
            studentRepository.deleteById(id);
        }
}
