package com.stiw2124.assignment1_group4.service;

import com.stiw2124.assignment1_group4.model.Book;
import com.stiw2124.assignment1_group4.repository.BookRepository;
// importing booknotfoundexception
import com.stiw2124.assignment1_group4.exception.BookNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    // Construc Injection
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    /*@Override
    @Transactional
    public Book save(Book book) {
        bookRepository.deleteById(id);
    }*/
    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }


    @Override
    public Page<Book> searchBooks(String q, Pageable pageable) {

        // If the user searches for something, use the repository search method
        if (q != null && !q.trim().isEmpty()) {
            return bookRepository.searchByKeyword(q, pageable);
        }

        // If the search is empty, just return all books
        return findAll(pageable);
    }


    // Update book
    @Override
    @Transactional
    public Book update(Long id, Book bookDetails){
        // before booknotfoundexception
        //  Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found."));
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found."));
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setCategory(bookDetails.getCategory());
        book.setShortDesc(bookDetails.getShortDesc());

        return bookRepository.save(book);
    }

}
