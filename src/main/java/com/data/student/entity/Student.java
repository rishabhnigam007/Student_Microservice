package com.data.student.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity representing a student.
 * This class is mapped to a database table and stores persistent student information.
 */
@Data
@Entity
@Table(name = "students")
@Schema(
        name = "Student",
        description = "Database entity representing a student record"
)
public class Student {

    /**
     * Unique identifier of the student.
     * Generated automatically by the persistence provider.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Primary key of the student record",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    /**
     * Name of the student.
     */
    @Column(nullable = false, length = 30)
    @Schema(
            description = "Name of the student",
            example = "Rishabh",
            minLength = 3,
            maxLength = 30,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String name;

    /**
     * Email address of the student.
     */
    @Column(nullable = false, unique = true, length = 100)
    @Schema(
            description = "Unique email address of the student",
            example = "rishabh@gmail.com",
            format = "email",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String email;
}
