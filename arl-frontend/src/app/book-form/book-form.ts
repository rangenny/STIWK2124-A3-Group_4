import { Component, OnInit, inject, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BookService, Book } from '../services/book';

@Component({
  selector: 'app-book-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './book-form.html',
  styleUrls: ['./book-form.css']
})
export class BookFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private bookService = inject(BookService);

  @Input() editTargetBook: Book | null = null; // Passed from parent if editing
  @Output() formViewFinished = new EventEmitter<void>(); // Signals parent to return to the list grid layout

  bookForm!: FormGroup;
  isEditMode = false;

  ngOnInit(): void {
    this.bookForm = this.fb.group({
      title: ['', Validators.required],
      author: ['', Validators.required],
      category: ['', Validators.required],
      shortDesc: ['', Validators.required]
    });

    // Check if we are editing an existing record passed down from the parent layout
    if (this.editTargetBook) {
      this.isEditMode = true;
      this.bookForm.patchValue(this.editTargetBook);
    }
  }

  get f() {
    return this.bookForm.controls;
  }

  onSubmit(): void {
    if (this.bookForm.invalid) {
      this.bookForm.markAllAsTouched();
      return;
    }

    const formData = this.bookForm.value;

    if (this.isEditMode && this.editTargetBook?.id) {
      this.bookService.updateBook(this.editTargetBook.id, formData).subscribe({
        next: () => {
          alert('Book details updated successfully!');
          this.formViewFinished.emit();
        },
        error: (err) => console.error('Update operation failed:', err)
      });
    } else {
      this.bookService.createBook(formData).subscribe({
        next: () => {
          alert('New book added to catalog successfully!');
          this.formViewFinished.emit();
        },
        error: (err) => console.error('Save operation failed:', err)
      });
    }
  }

  onCancel(): void {
    this.formViewFinished.emit();
  }
}