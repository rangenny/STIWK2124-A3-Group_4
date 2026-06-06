import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Book {
  id?: string | number;
  title: string;
  author: string;
  category: string;
  shortDesc: string;
}

export interface SpringPageResponse {
  content: Book[];
  totalPages: number;
  totalElements: number;
  number: number;
  size: number;
}

@Injectable({
  providedIn: 'root',
})
export class BookService {
  private http = inject(HttpClient);
  private apiUrl = 'http://127.0.0.1:8080/api/books';

  // 1. Fetching books (with optional search for the backend)
  // Update this specific line in src/app/services/book.ts
  getBooks(page: number, size: number, search: string = ''): Observable<SpringPageResponse> {
    // The backend uses 'q' as the parameter name
    let url = `${this.apiUrl}?page=${page}&size=${size}`;
    if (search.trim()) {
      url += `&q=${encodeURIComponent(search.trim())}`; 
    }
    return this.http.get<SpringPageResponse>(url);
  }

  // 2. Needed by your Form to create new data
  createBook(bookData: Book): Observable<Book> {
    return this.http.post<Book>(this.apiUrl, bookData);
  }

  // 3. Needed by your Form to update existing data
  updateBook(id: string | number, bookData: Book): Observable<Book> {
    return this.http.put<Book>(`${this.apiUrl}/${id}`, bookData);
  }

  // 4. Needed for your Delete functionality
  deleteBook(id: string | number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}