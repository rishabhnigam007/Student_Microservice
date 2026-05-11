package com.data.student.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Data Transfer Object (DTO) used for adding a new student.
 * This class carries the required input data for creating a student and includes validation constraints to ensure data integrity.
 */
@Data
@Schema(
        name = "AddStudentRequestDto",
        description = "Request payload for creating a new student"
)
public class AddStudentRequestDto {

    /**
     * Name of the student.
     * Must not be blank and must contain between 3 and 30 characters.
     */
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 30, message = "Name should be of length 3 to 30 characters")
    @Schema(
            description = "Full name of the student (3 to 30 characters)",
            example = "Rishabh",
            minLength = 3,
            maxLength = 30,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String name;

    /**
     * Email address of the student.
     * Must not be blank and must be a valid email format.
     */
    @Email
    @NotBlank(message = "Email is Required")
    @Schema(
            description = "Valid email address of the student",
            example = "rishabh@gmail.com",
            format = "email",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String email;
}
