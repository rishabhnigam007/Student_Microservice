package com.data.student.repository;

import com.data.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Student entities.
 * Provides CRUD operations and query execution capabilities through Spring Data JPA.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);
}
