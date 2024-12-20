import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ClienteService } from '../../../services/cliente.service';
import { Cliente } from '../../../models/cliente.model';

@Component({
  selector: 'app-cliente-register',
  standalone: true, // Standalone component
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './cliente.component.html',
  styleUrls: ['../registro.component.css'],
})
export class ClienteRegisterComponent {
  clienteForm: FormGroup;
  isLoading = false;
  errorMessage = '';

  constructor(private fb: FormBuilder, private registroClienteService: ClienteService) {
    this.clienteForm = this.fb.group({
      apellido: ['', [Validators.required, Validators.maxLength(50)]],
      nombre: ['', [Validators.required, Validators.maxLength(50)]],
      dni: ['', [Validators.required, Validators.pattern(/^\d{7,8}$/)]],
      email: ['', [Validators.required, Validators.email]],
      clave: ['', [Validators.required, Validators.minLength(4)]],
      vegetariano: [false],
    });
  }

  onSubmit() {
    console.log("im here");
    if (this.clienteForm.valid) {
      const cliente: Cliente = this.clienteForm.value;

      this.isLoading = true;
      this.errorMessage = '';

      this.registroClienteService.registrarCliente(cliente).subscribe({
        next: (response) => {
          console.log('Cliente registered:', response);
          alert('Cliente registrado exitosamente.');
          this.clienteForm.reset(); // Reset form after successful registration
        },
        error: (error) => {
          console.error('Error registering cliente:', error);
          this.errorMessage = 'Ocurrió un error al registrar el cliente.';
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
