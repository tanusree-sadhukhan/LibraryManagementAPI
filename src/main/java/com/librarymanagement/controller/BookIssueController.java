package com.librarymanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.librarymanagement.entity.BookIssue;
import com.librarymanagement.service.BookIssueService;

@RestController
@RequestMapping("/api/issues")
public class BookIssueController {

    private final BookIssueService issueService;

    public BookIssueController(BookIssueService issueService) {
        this.issueService = issueService;
    }

    // Issue a book
    @PostMapping("/{studentId}/{bookId}")
    public BookIssue issueBook(
            @PathVariable Long studentId,
            @PathVariable Long bookId) {
        return issueService.issueBook(studentId, bookId);
    }

    // Get all issue records
    @GetMapping
    public List<BookIssue> getAllIssues() {
        return issueService.getAllIssues();
    }

    // Get issues by student
    @GetMapping("/student/{studentId}")
    public List<BookIssue> getIssuesByStudent(
            @PathVariable Long studentId) {
        return issueService.getIssuesByStudent(studentId);
    }

    // Return a book
    @PutMapping("/return/{issueId}")
    public BookIssue returnBook(
            @PathVariable Long issueId) {
        return issueService.returnBook(issueId);
    }

    // Get overdue books
    @GetMapping("/overdue")
    public List<BookIssue> getOverdueIssues() {
        return issueService.getOverdueIssues();
    }
}