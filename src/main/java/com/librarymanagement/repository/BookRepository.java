package com.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.librarymanagement.entity.Book;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByCategory(String category);
    boolean existsByIsbn(String isbn);

}