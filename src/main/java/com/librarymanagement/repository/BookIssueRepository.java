package com.librarymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagement.entity.BookIssue;

public interface BookIssueRepository extends JpaRepository<BookIssue, Long> {

    List<BookIssue> findByStudentStudentId(Long studentId);

    List<BookIssue> findByStatus(String status);

    long countByStudentStudentIdAndStatus(Long studentId, String status);
}