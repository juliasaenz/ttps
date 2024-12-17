import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-register',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './admin.component.html',
  styleUrls: ['../registro.component.css']
})
export class AdminRegisterComponent {
  adminForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.adminForm = this.fb.group({
      apellido: ['', [Validators.required, Validators.maxLength(50)]],
      nombre: ['', [Validators.required, Validators.maxLength(50)]],
      dni: ['', [Validators.required, Validators.pattern(/^\d{7,8}$/)]],
      email: ['', [Validators.required, Validators.email]],
      clave: ['', [Validators.required, Validators.minLength(4)]],
    });
  }

/* TODO: Conectar con el backend */
  onSubmit() {
    if (this.adminForm.valid) {
      console.log('Form Data:', this.adminForm.value);
    } else {
      console.log('Form is invalid');
    }
  }
}
