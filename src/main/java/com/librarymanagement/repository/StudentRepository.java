package com.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.librarymanagement.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);

}
