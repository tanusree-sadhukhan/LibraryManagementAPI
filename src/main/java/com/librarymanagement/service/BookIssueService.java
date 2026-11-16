
package com.librarymanagement.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.librarymanagement.entity.Book;
import com.librarymanagement.entity.BookIssue;
import com.librarymanagement.entity.Student;
import com.librarymanagement.repository.BookIssueRepository;
import com.librarymanagement.repository.BookRepository;
import com.librarymanagement.repository.StudentRepository;
import com.librarymanagement.exception.ResourceNotFoundException;

@Service
public class BookIssueService {

    private final BookIssueRepository issueRepository;
    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;

    public BookIssueService(BookIssueRepository issueRepository,
                            BookRepository bookRepository,
                            StudentRepository studentRepository) {
        this.issueRepository = issueRepository;
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }

    // Issue a book
    @Transactional
    public BookIssue issueBook(Long studentId, Long bookId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Student not found!"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Book not found!"));

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No copies available!");
        }

        long activeIssues =
                issueRepository.countByStudentStudentIdAndStatus(
                        studentId, "ISSUED");

        if (activeIssues >= 3) {
            throw new RuntimeException(
                    "Student cannot issue more than 3 books!");
        }

        BookIssue issue = new BookIssue();
        issue.setStudent(student);
        issue.setBook(book);
        issue.setIssueDate(LocalDate.now());
        issue.setDueDate(LocalDate.now().plusDays(14));
        issue.setStatus("ISSUED");

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        return issueRepository.save(issue);
    }

    // Get all issues
    public List<BookIssue> getAllIssues() {
        return issueRepository.findAll();
    }

    // Get issues by student
    public List<BookIssue> getIssuesByStudent(Long studentId) {
        return issueRepository.findByStudentStudentId(studentId);
    }

    // Return a book
    @Transactional
    public BookIssue returnBook(Long issueId) {

        BookIssue issue = issueRepository.findById(issueId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Issue record not found!"));

        if ("RETURNED".equals(issue.getStatus())) {
            throw new IllegalStateException("Book already returned!");
        }

        issue.setReturnDate(LocalDate.now());
        issue.setStatus("RETURNED");

        Book book = issue.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return issueRepository.save(issue);
    }

    // Get overdue issues
    public List<BookIssue> getOverdueIssues() {
        return issueRepository.findByStatus("ISSUED")
                .stream()
                .filter(issue ->
                    issue.getDueDate().isBefore(LocalDate.now()))
                .toList();
    }
}
