package com.data.student.service.impl;

import com.data.student.dto.AddStudentRequestDto;
import com.data.student.dto.StudentDto;
import com.data.student.entity.Student;
import com.data.student.exception.DuplicateEmailException;
import com.data.student.exception.StudentNotFoundException;
import com.data.student.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;
    private StudentDto studentDto;
    private AddStudentRequestDto requestDto;

    @BeforeEach
    void setUp() {

        student = new Student();
        student.setId(1L);
        student.setName("Rishabh");
        student.setEmail("r@gmail.com");

        studentDto = new StudentDto();
        studentDto.setName("Rishabh");
        studentDto.setEmail("r@gmail.com");

        requestDto = new AddStudentRequestDto();
        requestDto.setName("Rishabh");
        requestDto.setEmail("r@gmail.com");
    }

    // ================= CREATE =================

    @Test
    void createStudent_Success() {

        when(modelMapper.map(requestDto, Student.class))
                .thenReturn(student);

        when(studentRepository.save(any(Student.class)))
                .thenReturn(student);

        when(modelMapper.map(student, StudentDto.class))
                .thenReturn(studentDto);

        StudentDto result =
                studentService.createNewStudent(requestDto);

        assertNotNull(result);
        assertEquals("Rishabh", result.getName());

        verify(studentRepository).save(any());
    }

    @Test
    void createStudent_DuplicateEmail() {

        when(studentRepository.existsByEmail("r@gmail.com"))
                .thenReturn(true);

        assertThrows(DuplicateEmailException.class, () ->
                studentService.createNewStudent(requestDto));

        verify(studentRepository, never()).save(any());
    }

    // ================= GET ALL =================

    @Test
    void getAllStudents_Success() {

        when(studentRepository.findAll())
                .thenReturn(List.of(student));

        when(modelMapper.map(student, StudentDto.class))
                .thenReturn(studentDto);

        List<StudentDto> list =
                studentService.getAllStudents();

        assertEquals(1, list.size());

        verify(studentRepository).findAll();
    }

    @Test
    void getAllStudents_Empty() {

        when(studentRepository.findAll())
                .thenReturn(List.of());

        List<StudentDto> list =
                studentService.getAllStudents();

        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    // ================= GET BY ID =================

    @Test
    void getStudentById_Success() {

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        when(modelMapper.map(student, StudentDto.class))
                .thenReturn(studentDto);

        StudentDto result =
                studentService.getStudentById(1L);

        assertNotNull(result);
        assertEquals("r@gmail.com", result.getEmail());
    }

    @Test
    void getStudentById_NotFound() {

        when(studentRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () ->
                studentService.getStudentById(1L));
    }

    // ================= DELETE =================

    @Test
    void deleteStudent_Success() {

        when(studentRepository.existsById(1L))
                .thenReturn(true);

        doNothing().when(studentRepository)
                .deleteById(1L);

        studentService.deleteStudentById(1L);

        verify(studentRepository).deleteById(1L);
    }

    @Test
    void deleteStudent_NotFound() {

        when(studentRepository.existsById(1L))
                .thenReturn(false);

        assertThrows(StudentNotFoundException.class, () ->
                studentService.deleteStudentById(1L));
    }

    // ================= UPDATE =================

    @Test
    void updateStudent_Success() {

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        doNothing().when(modelMapper)
                .map(requestDto, student);

        when(studentRepository.save(student))
                .thenReturn(student);

        when(modelMapper.map(student, StudentDto.class))
                .thenReturn(studentDto);

        StudentDto result =
                studentService.updateStudent(1L, requestDto);

        assertNotNull(result);

        verify(studentRepository).save(student);
    }

    @Test
    void updateStudent_NotFound() {

        when(studentRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () ->
                studentService.updateStudent(1L, requestDto));
    }

    @Test
    void updateStudent_DuplicateEmail() {

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        when(studentRepository.existsByEmail("r@gmail.com"))
                .thenReturn(true);

        student.setEmail("old@gmail.com");

        assertThrows(DuplicateEmailException.class, () ->
                studentService.updateStudent(1L, requestDto));
    }

    // ================= PATCH =================

    @Test
    void updatePartialStudent_Success() {

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        when(studentRepository.save(any()))
                .thenReturn(student);

        when(modelMapper.map(student, StudentDto.class))
                .thenReturn(studentDto);

        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "NewName");
        updates.put("email", "new@gmail.com");

        StudentDto result =
                studentService.updatePartialStudent(1L, updates);

        assertNotNull(result);

        verify(studentRepository).save(student);
    }

    @Test
    void updatePartialStudent_InvalidField() {

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        Map<String, Object> updates = new HashMap<>();
        updates.put("invalid", "test");

        assertThrows(IllegalArgumentException.class, () ->
                studentService.updatePartialStudent(1L, updates));
    }

    @Test
    void updatePartialStudent_DuplicateEmail() {

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        when(studentRepository.existsByEmail("new@gmail.com"))
                .thenReturn(true);

        student.setEmail("old@gmail.com");

        Map<String, Object> updates = new HashMap<>();
        updates.put("email", "new@gmail.com");

        assertThrows(DuplicateEmailException.class, () ->
                studentService.updatePartialStudent(1L, updates));
    }

    @Test
    void updatePartialStudent_NotFound() {

        when(studentRepository.findById(1L))
                .thenReturn(Optional.empty());

        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Test");

        assertThrows(StudentNotFoundException.class, () ->
                studentService.updatePartialStudent(1L, updates));
    }
}
