package com.data.student.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Standard API error response model.
 * Used to represent error details returned by the application.
 */
@Data
@AllArgsConstructor
@Schema(
        name = "ApiError",
        description = "Standard structure for API error responses"
)
public class ApiError {

    @Schema(
            description = "HTTP status code of the error",
            example = "404"
    )
    private int status;

    @Schema(
            description = "Human-readable description of the error",
            example = "Student not found with id : 10"
    )
    private String message;

    @Schema(
            description = "Timestamp when the error occurred",
            example = "2026-02-03T16:45:12"
    )
    private LocalDateTime timestamp;
}
