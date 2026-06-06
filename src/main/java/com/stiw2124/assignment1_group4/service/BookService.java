package com.stiw2124.assignment1_group4.service;

import com.stiw2124.assignment1_group4.model.Book; // This will show an error until Person A makes the Book class
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface BookService {
    // Basic CRUD
    Page<Book> findAll(Pageable pageable);

    Optional<Book> findById(Long id);

    Book save(Book book);

    void deleteById(Long id);

    // Pagination & Search Logic
    Page<Book> searchBooks(String q, Pageable pageable);

    // Update Book
    Book update(Long id, Book bookdetails);
}
