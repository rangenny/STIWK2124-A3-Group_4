import { Component, OnInit, Output, EventEmitter, ChangeDetectorRef, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BookService, Book, SpringPageResponse } from '../../services/book';

@Component({
  selector: 'app-book-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './book-list.html',
  styleUrls: ['./book-list.css']
})
export class BookList implements OnInit {
  @Output() editRequested = new EventEmitter<Book>();

  private bookService = inject(BookService);
  private cdr = inject(ChangeDetectorRef);

  books: Book[] = [];
  searchTerm: string = '';
  currentPage: number = 0;
  totalPages: number = 0;
  pageSize: number = 9; // Ensure this matches your backend default

  ngOnInit(): void {
    // Initial load: no search term
    this.loadBooks();
  }

readAloud(title: string, author: string, description: string) {
    const speech = new SpeechSynthesisUtterance();
    
    const descToRead = description ? description : 'No description available.';
    const authorToRead = author ? author : 'Unknown author';
    
    speech.text = `Title: ${title}. By ${authorToRead}. Description: ${descToRead}`;
    window.speechSynthesis.speak(speech);
  }
  
  loadBooks(): void {
    // We send the current page and current searchTerm to the backend
    this.bookService.getBooks(this.currentPage, this.pageSize, this.searchTerm).subscribe({
      next: (response: SpringPageResponse) => {
        this.books = response.content || [];
        this.totalPages = response.totalPages || 1;
        // Keep currentPage in sync with backend response
        this.currentPage = response.number;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Failed to communicate with catalog database endpoint:', err);
        this.books = []; // Clear list on error
        this.cdr.detectChanges();
      }
    });
  }

  onSearch(event: Event): void {
    const input = event.target as HTMLInputElement;
    this.searchTerm = input.value;
    
    // RESET: Searching must always start from page 1 to be accurate
    this.currentPage = 0;
    
    // Call loadBooks, which will now use the updated this.searchTerm
    this.loadBooks();
  }

  onPageChange(newPage: number): void {
    if (newPage >= 0 && newPage <= this.totalPages) {
      this.currentPage = newPage;
      this.loadBooks();
    }
  }
  

  onDeleteClick(id: string | number | undefined): void {
    if (id && confirm('Delete this book?')) {
      this.bookService.deleteBook(id).subscribe(() => this.loadBooks());
    }
  }

  onEditClick(book: Book): void {
    this.editRequested.emit(book);
  }
}