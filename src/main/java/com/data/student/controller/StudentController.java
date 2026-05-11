package com.data.student.controller;

import com.data.student.dto.AddStudentRequestDto;
import com.data.student.dto.ApiError;
import com.data.student.dto.StudentDto;
import com.data.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller responsible for managing student resources.
 * Provides CRUD operations for students and exposes endpoints under the /students path.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
@CrossOrigin("http://localhost:4200")
@Tag(
        name = "Student",
        description = "APIs for managing students (Create, Read, Update, Delete)"
)
@Slf4j
public class StudentController {

    /**
     * Service layer used to handle student-related business logic.
     */
    private final StudentService studentService;

    /**
     * Retrieves all students.
     *
     * @return a list of all students wrapped in a ResponseEntity
     */
    @Operation(
            summary = "Get all students",
            description = "Fetches the list of all registered students"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Students fetched successfully"
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Blank list return []"
            )
    })
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        log.info("GET /students - Fetching all students");

        List<StudentDto> students = studentService.getAllStudents();

        log.info("GET /students - Fetched {} students", students.size());
        log.debug("Student list: {}", students);

        return ResponseEntity.ok(students);
    }

    /**
     * Retrieves a student by its unique identifier.
     *
     * @param id the ID of the student to retrieve
     * @return the corresponding student data
     */
    @Operation(
            summary = "Get student by ID",
            description = "Fetches a student using their unique ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Student found successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found",
                    content = @Content(
                            schema = @Schema(implementation = ApiError.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@Parameter(
            description = "Unique ID of the student",
            example = "1",
            required = true
    ) @PathVariable Long id) {
        log.info("GET /students/{} - Fetching student", id);

        StudentDto student = studentService.getStudentById(id);

        log.info("GET /students/{} - Student fetched successfully", id);
        log.debug("Student data: {}", student);

        return ResponseEntity.ok(student);
    }

    /**
     * Creates a new student.
     * The request body is validated before processing.
     *
     * @param addStudentRequestDto the data required to create a new student
     * @return the created student with HTTP status 201 CREATED
     */
    @Operation(
            summary = "Create a new student",
            description = "Creates and saves a new student in the system"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Student created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed",
                    content = @Content(
                            schema = @Schema(implementation = ApiError.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email already exists",
                    content = @Content(
                            schema = @Schema(implementation = ApiError.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<StudentDto> createNewStudent(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Student creation request payload",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = AddStudentRequestDto.class)
            )
    ) @Valid @RequestBody AddStudentRequestDto addStudentRequestDto) {
        log.info("POST /students - Creating new student");
        log.debug("Create request payload: {}", addStudentRequestDto);

        StudentDto createdStudent = studentService.createNewStudent(addStudentRequestDto);

        log.info("POST /students - Student created with ID: {}", createdStudent.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    /**
     * Deletes a student by its unique identifier.
     *
     * @param id the ID of the student to delete
     * @return an empty response with HTTP status 204 NO CONTENT
     */
    @Operation(
            summary = "Delete student",
            description = "Deletes a student using their ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Student deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found",
                    content = @Content(
                            schema = @Schema(implementation = ApiError.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAStudent(@Parameter(
            description = "ID of the student to delete",
            example = "101",
            required = true
    ) @PathVariable Long id) {
        log.info("DELETE /students/{} - Deleting student", id);

        studentService.deleteStudentById(id);

        log.info("DELETE /students/{} - Student deleted successfully", id);

        return ResponseEntity.noContent().build();
    }

    /**
     * Updates an existing student completely.
     *
     * @param id                   the ID of the student to update
     * @param addStudentRequestDto the updated student data
     * @return the updated student information
     */
    @Operation(
            summary = "Update student (Full)",
            description = "Completely updates an existing student"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Student updated successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found",
                    content = @Content(
                            schema = @Schema(implementation = ApiError.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email already exists",
                    content = @Content(
                            schema = @Schema(implementation = ApiError.class)
                    )
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@Parameter(
                                                            description = "ID of the student to update",
                                                            example = "1",
                                                            required = true
                                                    )
                                                    @PathVariable Long id,
                                                    @RequestBody AddStudentRequestDto addStudentRequestDto) {
        log.info("PUT /students/{} - Updating student (full)", id);
        log.debug("Update payload: {}", addStudentRequestDto);

        StudentDto updatedStudent = studentService.updateStudent(id, addStudentRequestDto);

        log.info("PUT /students/{} - Student updated successfully", id);

        return ResponseEntity.ok(updatedStudent);
    }

    /**
     * Partially updates an existing student.
     * Only the fields provided in the update map will be modified.
     *
     * @param id      the ID of the student to update
     * @param updates a map containing fields to update and their new values
     * @return the updated student information
     */
    @Operation(
            summary = "Update student (Partial)",
            description = "Updates selected fields of an existing student"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Student partially updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid field provided",
                    content = @Content(
                            schema = @Schema(implementation = ApiError.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found",
                    content = @Content(
                            schema = @Schema(implementation = ApiError.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email already exists",
                    content = @Content(
                            schema = @Schema(implementation = ApiError.class)
                    )
            )
    })
    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> updatePartialStudent(@Parameter(
                                                                   description = "ID of the student to update",
                                                                   example = "1",
                                                                   required = true
                                                           )
                                                           @PathVariable Long id,
                                                           @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                                   description = "Fields to update (key-value pairs)",
                                                                   required = true,
                                                                   content = @Content(
                                                                           schema = @Schema(implementation = AddStudentRequestDto.class)
                                                                   )
                                                           )
                                                           @RequestBody Map<String, Object> updates) {
        log.info("PATCH /students/{} - Partial update", id);
        log.debug("Partial update fields: {}", updates);

        StudentDto updatedStudent =
                studentService.updatePartialStudent(id, updates);

        log.info("PATCH /students/{} - Partial update successful", id);

        return ResponseEntity.ok(updatedStudent);
    }
}
