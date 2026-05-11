package com.data.student.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Exception thrown when a student resource cannot be found.
 * This runtime exception is typically raised when a lookup
 * operation fails to locate a student using a specific field and value.
 */
@Getter
@Setter
public class StudentNotFoundException extends RuntimeException {

    /**
     * Name of the resource that was not found.
     */
    private final String resourceName;

    /**
     * Name of the field used in the lookup.
     */
    private final String fieldName;

    /**
     * Value of the field used in the lookup.
     */
    private final long fieldValue;

    /**
     * Constructs a new StudentNotFoundException.
     *
     * @param resourceName the name of the missing resource
     * @param fieldName    the field used to search for the resource
     * @param fieldValue   the value of the field used in the lookup
     */
    public StudentNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
