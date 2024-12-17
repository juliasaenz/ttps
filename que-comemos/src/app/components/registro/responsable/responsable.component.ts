import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-responsable-register',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './responsable.component.html',
  styleUrls: ['../registro.component.css']
})
export class ResponsableRegisterComponent {
  responsableForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.responsableForm = this.fb.group({
      apellido: ['', [Validators.required, Validators.maxLength(50)]],
      nombre: ['', [Validators.required, Validators.maxLength(50)]],
      dni: ['', [Validators.required, Validators.pattern(/^\d{7,8}$/)]],
      email: ['', [Validators.required, Validators.email]],
      clave: ['', [Validators.required, Validators.minLength(4)]],
      turno: ['', [Validators.required]]
    });
  }

/* TODO: Conectar con el backend */
  onSubmit() {
    if (this.responsableForm.valid) {
      console.log('Form Data:', this.responsableForm.value);
    } else {
      console.log('Form is invalid');
    }
  }
}
