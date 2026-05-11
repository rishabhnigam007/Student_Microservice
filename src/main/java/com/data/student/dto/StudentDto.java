package com.data.student.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a student.
 * This class is used to transfer student related data between layers of the application.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "StudentDto",
        description = "Represents student information returned by the API"
)
public class StudentDto {

    /**
     * Unique identifier of the student.
     */
    @Schema(
            description = "Unique identifier of the student",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    /**
     * name of the student.
     */
    @Schema(
            description = "Name of the student",
            example = "Rishabh",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String name;

    /**
     * Email address of the student.
     */
    @Schema(
            description = "Email address of the student",
            example = "rishabh@gmail.com",
            requiredMode = Schema.RequiredMode.REQUIRED,
            format = "email"
    )
    private String email;
}
