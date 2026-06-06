package com.stiw2124.assignment1_group4.controller;

import com.stiw2124.assignment1_group4.model.Book;
import com.stiw2124.assignment1_group4.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

    @Autowired
    private BookService bookService; 

    //GET all books or search
    @GetMapping
    public ResponseEntity<Page<Book>> getBooks(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "9") int size,
        @RequestParam(required = false) String q) {

            Pageable pageable = PageRequest.of(page, size);

            //if "q" is present, use searchBooks, otherwise use findAll
            if (q != null && !q.isEmpty()){
                return ResponseEntity.ok(bookService.searchBooks(q, pageable));
            }
            return ResponseEntity.ok(bookService.findAll(pageable));
            
    }

    //GET book by id
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        return bookService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //POST new book
    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book){
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    //PUT to update a book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book book){
        return ResponseEntity.ok(bookService.update(id, book));
    }

    //DELETE book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
