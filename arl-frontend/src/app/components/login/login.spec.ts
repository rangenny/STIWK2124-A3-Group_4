import { TestBed } from '@angular/core/testing';
import { vi } from 'vitest';
import { of, throwError } from 'rxjs';
import { LoginComponent } from './login';
import { AuthService } from '../../services/auth';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let loginSpy: ReturnType<typeof vi.fn>;

  beforeEach(async () => {
    // Mock AuthService so the test never touches the real backend.
    loginSpy = vi.fn();

    await TestBed.configureTestingModule({
      imports: [LoginComponent],
      providers: [{ provide: AuthService, useValue: { login: loginSpy } }],
    }).compileComponents();

    const fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should show an error and NOT call the service when fields are empty', () => {
    component.username = '';
    component.password = '';

    component.onSubmit();

    expect(component.errorMessage).toBe('Please enter both username and password.');
    expect(loginSpy).not.toHaveBeenCalled();
  });

  it('should call AuthService.login with the entered credentials', () => {
    loginSpy.mockReturnValue(of({ success: true, username: 'admin' }));
    component.username = 'admin';
    component.password = 'admin123';

    component.onSubmit();

    expect(loginSpy).toHaveBeenCalledWith('admin', 'admin123');
    expect(component.errorMessage).toBe('');
  });

  it('should display the server error message on a failed login', () => {
    loginSpy.mockReturnValue(
      throwError(() => ({ error: { message: 'Invalid username or password' } }))
    );
    component.username = 'admin';
    component.password = 'wrong';

    component.onSubmit();

    expect(component.errorMessage).toBe('Invalid username or password');
    expect(component.loading).toBe(false);
  });
});
