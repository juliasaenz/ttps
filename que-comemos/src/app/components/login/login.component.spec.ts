import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthService } from '../../services/auth.service';
import { of, throwError } from 'rxjs';
import { LoginComponent } from './login.component';
import { FormsModule } from '@angular/forms';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authService: AuthService;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        LoginComponent,
        HttpClientTestingModule,
        RouterTestingModule,
        FormsModule,
      ],
      providers: [AuthService],
    }).compileComponents();

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    authService = TestBed.inject(AuthService);
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call AuthService.login and navigate on successful login', () => {
    spyOn(authService, 'login').and.returnValue(of({ token: 'test-token' }));
    spyOn(router, 'navigate');

    component.email = 'test@mail.com';
    component.password = 'password123';
    component.login();

    expect(authService.login).toHaveBeenCalledWith({
      email: 'test@mail.com',
      password: 'password123',
    });
    expect(router.navigate).toHaveBeenCalledWith(['/carta']);
  });

  it('should display an alert on login error', () => {
    spyOn(authService, 'login').and.returnValue(throwError(() => new Error('Login failed')));
    spyOn(window, 'alert');

    component.email = 'test@mail.com';
    component.password = 'wrongpassword';
    component.login();

    expect(authService.login).toHaveBeenCalled();
    expect(window.alert).toHaveBeenCalledWith('Credenciales incorrectas. Intenta nuevamente.');
  });
});
