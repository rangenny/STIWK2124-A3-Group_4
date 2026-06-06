import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'add-book',
    loadComponent: () => import('./book-form/book-form').then(m => m.BookFormComponent)
  },
  {
    path: 'edit-book/:id',
    loadComponent: () => import('./book-form/book-form').then(m => m.BookFormComponent)
  },
  {
    path: 'books',
    loadComponent: () => import('./components/book-list/book-list').then(m => m.BookList)
  },
  // CHANGE THIS REDIRECT LINE TO 'add-book' SO YOUR FORM LOADS BY DEFAULT
  {
    path: '',
    redirectTo: 'add-book',
    pathMatch: 'full'
  }
];