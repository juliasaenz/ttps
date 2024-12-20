import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ResponsableService } from '../../../services/responsable.service';
import { Responsable } from '../../../models/responsable.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-responsable-register',
  standalone: true, // Standalone component
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './responsable.component.html',
  styleUrls: ['../registro.component.css'],
})
export class ResponsableRegisterComponent {
  responsableForm: FormGroup;
  isLoading = false;
  errorMessage = '';

  constructor(private fb: FormBuilder, private registroResponsableService: ResponsableService, private router: Router) {
    this.responsableForm = this.fb.group({
      apellido: ['', [Validators.required, Validators.maxLength(50)]],
      nombre: ['', [Validators.required, Validators.maxLength(50)]],
      dni: ['', [Validators.required, Validators.pattern(/^\d{7,8}$/)]],
      email: ['', [Validators.required, Validators.email]],
      clave: ['', [Validators.required, Validators.minLength(4)]],
      turno: ['', [Validators.required]],
    });
  }

  onSubmit() {
    console.log("im here");
    if (this.responsableForm.valid) {
      const responsable: Responsable = this.responsableForm.value;

      this.isLoading = true;
      this.errorMessage = '';

      this.registroResponsableService.registrarResponsable(responsable).subscribe({
        next: (response) => {
          console.log('Responsable registered:', response);
          this.router.navigate(['/registro/exito']);
        },
        error: (error) => {
          console.error('Error registering responsable:', error);
          this.errorMessage = 'Ocurrió un error al registrar el responsable.';
          alert(this.errorMessage);
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
