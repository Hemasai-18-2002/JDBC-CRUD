package com.hcltech.Student.service;

import com.hcltech.Student.dto.ExamDto;
import com.hcltech.Student.model.Exam;
import com.hcltech.Student.repository.ExamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final ModelMapper modelMapper;

    public ExamService(ExamRepository examRepository, ModelMapper modelMapper) {
        this.examRepository = examRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<ExamDto> getAllExams() {
        return examRepository.findAll().stream()
                .map(exam -> modelMapper.map(exam, ExamDto.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ExamDto getExamById(Integer id) {
        Optional<Exam> examOptional = examRepository.findById(id);
        if (examOptional.isEmpty()) {
            // In a real application, you'd throw a custom NotFoundException
            throw new RuntimeException("Exam not found with id: " + id);
        }
        return modelMapper.map(examOptional.get(), ExamDto.class);
    }

    @Transactional
    public ExamDto createExam(ExamDto examDto) {
        // Map DTO to Entity
        Exam exam = modelMapper.map(examDto, Exam.class);
        // Ensure ID is null for new entities
        exam.setId(null); // Important for auto-generated IDs

        Exam savedExam = examRepository.save(exam);
        // Map saved Entity back to DTO
        return modelMapper.map(savedExam, ExamDto.class);
    }

    @Transactional
    public ExamDto updateExam(ExamDto examDto) {
        if (examDto.getId() == null) {
            throw new IllegalArgumentException("Exam ID must be provided for update.");
        }
        Optional<Exam> existingExamOptional = examRepository.findById(examDto.getId());
        if (existingExamOptional.isEmpty()) {
            throw new RuntimeException("Exam not found with id: " + examDto.getId());
        }

        // Map DTO to existing Entity to update its fields
        Exam existingExam = existingExamOptional.get();
        modelMapper.map(examDto, existingExam); // Updates existingExam fields from examDto

        Exam updatedExam = examRepository.save(existingExam);
        return modelMapper.map(updatedExam, ExamDto.class);
    }

    @Transactional
    public void deleteExam(Integer id) {
        if (!examRepository.existsById(id)) {
            throw new RuntimeException("Exam not found with id: " + id);
        }
        examRepository.deleteById(id);
    }
}