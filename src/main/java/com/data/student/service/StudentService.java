package com.data.student.service;

import com.data.student.dto.AddStudentRequestDto;
import com.data.student.dto.StudentDto;

import java.util.List;
import java.util.Map;

/**
 * Service interface defining operations for managing students.
 * This service provides methods for retrieving, creating, updating,
 * and deleting student data. Implementations of this interface handle
 * the business logic and interact with the persistence layer.
 */
public interface StudentService {

    /**
     * Retrieves all students.
     *
     * @return a list of all StudentDto objects
     */
    List<StudentDto> getAllStudents();

    /**
     * Retrieves a student by its unique identifier.
     *
     * @param id the ID of the student to retrieve
     * @return the StudentDto of the student
     */
    StudentDto getStudentById(Long id);

    /**
     * Creates a new student.
     *
     * @param addStudentRequestDto the data required to create a new student
     * @return the created StudentDto
     */
    StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);

    /**
     * Deletes a student by its unique identifier.
     *
     * @param id the ID of the student to delete
     */
    void deleteStudentById(Long id);

    /**
     * Updates an existing student completely.
     *
     * @param id                   the ID of the student to update
     * @param addStudentRequestDto the updated student data
     * @return the updated StudentDto
     */
    StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto);

    /**
     * Partially updates an existing student.
     * Only the fields provided in the update map will be modified.
     *
     * @param id      the ID of the student to update
     * @param updates a map containing the fields to update and their new values
     * @return the updated StudentDto
     */
    StudentDto updatePartialStudent(Long id, Map<String, Object> updates);
}
