package com.data.student.service.impl;

import com.data.student.constant.StudentConstants;
import com.data.student.dto.AddStudentRequestDto;
import com.data.student.dto.StudentDto;
import com.data.student.entity.Student;
import com.data.student.exception.DuplicateEmailException;
import com.data.student.exception.StudentNotFoundException;
import com.data.student.repository.StudentRepository;
import com.data.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Implementation of StudentService for managing student data.
 * This service class contains the business logic for creating, retrieving,
 * updating, and deleting students. It interacts with the StudentRepository
 * for persistence and uses ModelMapper to convert between entities and DTOs.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    /**
     * Repository used for CRUD operations on Student entities.
     */
    private final StudentRepository studentRepository;

    /**
     * Mapper used to convert between entity and DTO objects.
     */
    private final ModelMapper modelMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<StudentDto> getAllStudents() {
        log.info("Fetching all students");

        List<Student> students = studentRepository.findAll();

        log.info("Fetched {} students", students.size());
        log.debug("Student entities: {}", students);

        return students.stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StudentDto getStudentById(Long id) {
        log.info("Fetching student with id: {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Student not found with id: {}", id);
                    return new StudentNotFoundException(StudentConstants.STUDENT, StudentConstants.ID, id);
                });

        log.info("Student found with id: {}", id);
        log.debug("Student entity: {}", student);

        return modelMapper.map(student, StudentDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {

        log.info("Creating new student with email: {}", addStudentRequestDto.getEmail());
        log.debug("Create request: {}", addStudentRequestDto);
        // check duplicate email before saving
        if (studentRepository.existsByEmail(addStudentRequestDto.getEmail())) {

            log.warn("Duplicate email detected: {}", addStudentRequestDto.getEmail());

            throw new DuplicateEmailException(addStudentRequestDto.getEmail());
        }

        Student newStudent = modelMapper.map(addStudentRequestDto, Student.class);

        Student savedStudent = studentRepository.save(newStudent);

        log.info("Student created successfully with id: {}", savedStudent.getId());

        return modelMapper.map(savedStudent, StudentDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteStudentById(Long id) {
        log.info("Deleting student with id: {}", id);

        if (!studentRepository.existsById(id)) {

            log.warn("Delete failed. Student not found with id: {}", id);

            throw new StudentNotFoundException(StudentConstants.STUDENT, StudentConstants.ID, id);
        }

        studentRepository.deleteById(id);

        log.info("Student deleted successfully with id: {}", id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto) {
        log.info("Updating student (full) with id: {}", id);
        log.debug("Update request: {}", addStudentRequestDto);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Update failed. Student not found with id: {}", id);
                    return new StudentNotFoundException(StudentConstants.STUDENT, StudentConstants.ID, id);
                });

        if (studentRepository.existsByEmail(addStudentRequestDto.getEmail())
                && !student.getEmail().equals(addStudentRequestDto.getEmail())) {

            log.warn("Duplicate email during update: {}", addStudentRequestDto.getEmail());

            throw new DuplicateEmailException(addStudentRequestDto.getEmail());
        }

        modelMapper.map(addStudentRequestDto, student);

        Student updatedStudent = studentRepository.save(student);

        log.info("Student updated successfully with id: {}", id);

        return modelMapper.map(updatedStudent, StudentDto.class);
    }

    /**
     * Performs a partial update of the student fields provided in the update map.
     * Currently, it supports updating "name" and "email" fields only.
     *
     * @throws IllegalArgumentException if an unsupported field is provided
     */
    @Override
    public StudentDto updatePartialStudent(Long id, Map<String, Object> updates) {

        log.info("Partially updating student with id: {}", id);
        log.debug("Partial update fields: {}", updates);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Partial update failed. Student not found with id: {}", id);
                    return new StudentNotFoundException(StudentConstants.STUDENT, StudentConstants.ID, id);
                });

        updates.forEach((field, value) -> {
            switch (field) {
                case "name" -> {
                    log.debug("Updating name for id: {}", id);
                    student.setName((String) value);
                }
                case "email" -> {
                    String newEmail = (String) value;
                    log.debug("Updating email for id: {}", id);
                    if (studentRepository.existsByEmail(newEmail)
                            && !student.getEmail().equals(newEmail)) {
                        log.warn("Duplicate email during partial update: {}", newEmail);
                        throw new DuplicateEmailException(newEmail);
                    }
                    student.setEmail(newEmail);
                }
                default -> {
                    log.warn("Invalid field '{}' for student id: {}", field, id);
                    throw new IllegalArgumentException(
                            "Unsupported field: " + field
                    );
                }
            }
        });
        Student savedStudent = studentRepository.save(student);
        log.info("Partial update successful for id: {}", id);
        return modelMapper.map(savedStudent, StudentDto.class);
    }
}
