import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookList } from './components/book-list/book-list';
import { BookFormComponent } from './book-form/book-form';
import { LoginComponent } from './components/login/login';
import { Book } from './services/book';
import { AuthService } from './services/auth';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, BookList, BookFormComponent, LoginComponent],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class AppComponent implements OnInit {
  title = 'assignment1-group4-frontend';

  auth = inject(AuthService);

  currentView: 'list' | 'form' = 'list';
  selectedBookForEdit: Book | null = null;

  ngOnInit(): void {
    this.currentView = 'list';
  }

  navigateToAddTask(): void {
    this.selectedBookForEdit = null;
    this.currentView = 'form';
  }

  navigateToEditTask(book: Book): void {
    this.selectedBookForEdit = book;
    this.currentView = 'form';
  }

  onFormFinished(): void {
    this.currentView = 'list';
    this.selectedBookForEdit = null;
  }

  logout(): void {
    this.auth.logout();
    this.currentView = 'list';
    this.selectedBookForEdit = null;
  }
}
