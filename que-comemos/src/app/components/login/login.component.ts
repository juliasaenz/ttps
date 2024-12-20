import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service'; // Ajusta la ruta según tu estructura de carpetas.

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  constructor(private authService: AuthService, private router: Router) {}

  login(event: Event): void {
    event.preventDefault(); // Evitar el comportamiento por defecto del formulario

    // Capturar valores de los inputs
    const form = event.target as HTMLFormElement;
    const email = (form.querySelector('#email') as HTMLInputElement).value;
    const password = (form.querySelector('#password') as HTMLInputElement).value;

    this.authService.login({ email, password }).subscribe({
      next: (response: { token: string }) => {
        localStorage.setItem('jwt', response.token); // Guarda el token en el localStorage
        alert('Cliente registrado exitosamente.');
        this.router.navigate(['/carta']);
      },
      error: (err) => {
        console.error('Login failed', err);
        alert('Credenciales incorrectas. Intenta nuevamente.');
      },
    });
  }
}
