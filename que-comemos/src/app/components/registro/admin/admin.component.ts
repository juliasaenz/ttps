import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AdminService } from '../../../services/admin.service';
import { Admin } from '../../../models/admin.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-register',
  standalone: true, // Standalone component
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './admin.component.html',
  styleUrls: ['../registro.component.css'],
})
export class AdminRegisterComponent {
  adminForm: FormGroup;
  isLoading = false;
  errorMessage = '';

  constructor(private fb: FormBuilder, private registroAdminService: AdminService, private router: Router) {
    this.adminForm = this.fb.group({
      apellido: ['', [Validators.required, Validators.maxLength(50)]],
      nombre: ['', [Validators.required, Validators.maxLength(50)]],
      dni: ['', [Validators.required, Validators.pattern(/^\d{7,8}$/)]],
      email: ['', [Validators.required, Validators.email]],
      clave: ['', [Validators.required, Validators.minLength(4)]],
    });
  }

  onSubmit() {
    console.log("im here");
    if (this.adminForm.valid) {
      const admin: Admin = this.adminForm.value;

      this.isLoading = true;
      this.errorMessage = '';

      this.registroAdminService.registrarAdmin(admin).subscribe({
        next: (response) => {
          console.log('Admin registered:', response);
          this.router.navigate(['/registro/exito']);
        },
        error: (error) => {
          console.error('Error registering admin:', error);
          this.errorMessage = 'Ocurrió un error al registrar el admin.';
        },
        complete: () => {
          this.isLoading = false;
        },
      });
    } else {
      console.log('Form is invalid');
    }
  }
}
