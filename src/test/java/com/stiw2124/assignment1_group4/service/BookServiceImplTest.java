package com.stiw2124.assignment1_group4.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.stiw2124.assignment1_group4.model.Book;
import com.stiw2124.assignment1_group4.repository.BookRepository;
import com.stiw2124.assignment1_group4.exception.BookNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    // 1. Mock the repository so we don't connect to a real database
    @Mock
    private BookRepository bookRepository;

    // 2. Inject the mock into the actual service we are testing
    @InjectMocks
    private BookServiceImpl bookService;

    private Book sampleBook;

    @BeforeEach
    void setUp() {
        // Create a dummy book before each test using the fields from your update()
        // method
        sampleBook = new Book();
        sampleBook.setTitle("Spring Boot Testing");
        sampleBook.setAuthor("John Doe");
        sampleBook.setCategory("Education");
        sampleBook.setShortDesc("A guide to backend testing.");
    }

    // ==========================================
    // TEST 1: SUCCESS PATH (Saving a Book)
    // ==========================================
    @Test
    void testSaveBook_Success() {
        // Arrange: Tell mock database what to return when save() is called
        when(bookRepository.save(any(Book.class))).thenReturn(sampleBook);

        // Act: Call the real service method
        Book savedBook = bookService.save(sampleBook);

        // Assert: Verify the results
        assertNotNull(savedBook, "The saved book should not be null");
        assertEquals("Spring Boot Testing", savedBook.getTitle());

        // Verify repository save method was called exactly once
        verify(bookRepository, times(1)).save(sampleBook);
    }

    // ==========================================
    // TEST 2: SUCCESS PATH (Finding a Book)
    // ==========================================
    @Test
    void testFindById_Success() {
        // Arrange: Mock the DB to return our sample book when searching for ID 1
        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleBook));

        // Act: Call the real service method
        Optional<Book> foundBook = bookService.findById(1L);

        // Assert: Verify the results
        assertTrue(foundBook.isPresent(), "Book should be found");
        assertEquals("John Doe", foundBook.get().getAuthor());

        // Verify repository findById method was called exactly once
        verify(bookRepository, times(1)).findById(1L);
    }

    // ==========================================
    // TEST 3: FAILURE PATH (Update Throws Exception)
    // ==========================================
    @Test
    void testUpdateBook_Failure_NotFound() {
        // Arrange: Mock the DB to return empty (book does not exist) for ID 99
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert: Check that your custom exception is thrown exactly as coded
        BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> {
            bookService.update(99L, sampleBook);
        });

        // Verify the exception message matches your exact code
        assertEquals("Book not found.", exception.getMessage());

        // Safety check: Verify the DB never tried to save anything because it failed
        // early
        verify(bookRepository, never()).save(any(Book.class));
    }
}
