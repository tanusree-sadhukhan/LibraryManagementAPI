
package com.librarymanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.librarymanagement.entity.Book;
import com.librarymanagement.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book addBook(@Valid @RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/category/{category}")
    public List<Book> getBooksByCategory(
            @PathVariable String category) {
        return bookService.getBooksByCategory(category);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id,
                           @Valid @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "Book deleted successfully!";
    }
}
