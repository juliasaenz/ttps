import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service'; 

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  role: 'clientes' | 'responsables' | 'administradores' | undefined;
  constructor(private authService: AuthService, private router: Router) { 
    this.role = undefined;
  }
  
  selectRole(selectedRole: 'clientes' | 'responsables' | 'administradores'): void {
    this.role = selectedRole;
    const rolePopup = document.getElementById('role-popup');
    if (this.role && rolePopup) {
      rolePopup.style.display = 'none';
    }
  }

  login(event: Event): void {
    event.preventDefault();

    // Capturar valores de los inputs
    const form = event.target as HTMLFormElement;
    const email = (form.querySelector('#email') as HTMLInputElement).value;
    const password = (form.querySelector('#password') as HTMLInputElement).value;
    if(!this.role){
      alert('Debes elegir un rol');
      return;
    }

    this.authService.login({ email, password }, this.role).subscribe({
      next: (response: { token: string }) => {
        localStorage.setItem('jwt', response.token);
        alert(this.role + ' registrado exitosamente.');
        this.router.navigate(['/carta']);
      },
      error: (err) => {
        console.error('Login failed', err);
        alert('Credenciales incorrectas. Intenta nuevamente.');
      },
    });
  }
}
