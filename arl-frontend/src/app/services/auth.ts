import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

export interface LoginResponse {
  success: boolean;
  username?: string;
  message?: string;
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private http = inject(HttpClient);
  private apiUrl = 'http://127.0.0.1:8080/api/auth';

  // Reactive flag the template uses to decide what to show.
  // Initialised from sessionStorage so a page refresh keeps you logged in.
  private _loggedIn = signal<boolean>(sessionStorage.getItem('loggedIn') === 'true');
  readonly isLoggedIn = this._loggedIn.asReadonly();

  // Send credentials to the backend; on success, flip the logged-in flag.
  login(username: string, password: string): Observable<LoginResponse> {
    return this.http
      .post<LoginResponse>(`${this.apiUrl}/login`, { username, password })
      .pipe(
        tap((res) => {
          if (res.success) {
            this._loggedIn.set(true);
            sessionStorage.setItem('loggedIn', 'true');
            sessionStorage.setItem('username', res.username ?? username);
          }
        })
      );
  }

  logout(): void {
    this._loggedIn.set(false);
    sessionStorage.removeItem('loggedIn');
    sessionStorage.removeItem('username');
  }

  get currentUser(): string | null {
    return sessionStorage.getItem('username');
  }
}
