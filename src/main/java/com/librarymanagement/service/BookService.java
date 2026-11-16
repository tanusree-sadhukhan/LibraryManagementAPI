
package com.librarymanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.librarymanagement.entity.Book;
import com.librarymanagement.repository.BookRepository;
import com.librarymanagement.exception.ResourceNotFoundException;
import com.librarymanagement.exception.DuplicateResourceException;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Add a new book
    public Book addBook(Book book) {

        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new DuplicateResourceException(
                    "ISBN already exists!");
        }

        return bookRepository.save(book);
    }

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get book by ID
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Book not found with ID: " + id));
    }

    // Get books by category
    public List<Book> getBooksByCategory(String category) {
        return bookRepository.findByCategory(category);
    }

    // Update book
    public Book updateBook(Long id, Book updatedBook) {

        Book existingBook = getBookById(id);

        if (!existingBook.getIsbn().equals(updatedBook.getIsbn())
                && bookRepository.existsByIsbn(updatedBook.getIsbn())) {
            throw new DuplicateResourceException(
                    "ISBN already exists!");
        }

        existingBook.updateDetails(updatedBook);

        return bookRepository.save(existingBook);
    }

    // Delete book
    public void deleteBook(Long id) {
        Book existingBook = getBookById(id);
        bookRepository.delete(existingBook);
    }
}
