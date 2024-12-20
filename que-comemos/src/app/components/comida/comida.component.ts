import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Comida } from '../../models/comida.model';
import { ComidaService } from '../../services/comida.service';
import { AuthService } from '../../services/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-comidas',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './comida.component.html',
  styleUrls: ['./comida.component.css'],
})
export class ComidaComponent implements OnInit, OnDestroy {
  comidas: Comida[] = [];
  newComida: Comida = new Comida();
  private errorMessage = '';
  isEditPopupVisible = false;
  editableComida: Comida = new Comida();

  isLoggedIn = false;
  userRole: 'clientes' | 'administradores' | 'responsables' | null = null;
  private subscriptions: Subscription = new Subscription();

  constructor(
    private comidaService: ComidaService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.subscriptions.add(
      this.authService.isAuthenticated$.subscribe((loggedIn) => {
        this.isLoggedIn = loggedIn;
        if (loggedIn) {
          this.getComidas(); 
        } else {
          this.comidas = []; 
        }
      })
    );

    this.subscriptions.add(
      this.authService.userRole$.subscribe((role) => {
        this.userRole = role;
        console.log('Rol del usuario actualizado:', role);
      })
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe(); 
  }

  private getComidas(): void {
    this.comidaService.getComidas().subscribe((data: Comida[]) => {
      this.comidas = data;
    });
  }

  private isNameUnique(comida: Comida): boolean {
    return !this.comidas.find(
      (existingComida) =>
        existingComida.id !== comida.id &&
        existingComida.nombre.toLowerCase() === comida.nombre.toLowerCase()
    );
  }

  addComida() {
    if (this.isNameUnique(this.newComida)) {
      const nombreFormatted =
        this.newComida.nombre.charAt(0).toUpperCase() +
        this.newComida.nombre.slice(1).toLowerCase();
      this.newComida.nombre = nombreFormatted;
      this.comidaService.addComidas(this.newComida).subscribe(
        (response) => {
          this.comidas.push(response);
          this.newComida = new Comida();
        },
        (error) => {
          this.errorMessage = 'Hubo un error al agregar la comida';
          console.error('Error agregando comida:', error);
          alert(this.errorMessage);
        }
      );
    } else {
      this.errorMessage = 'Ya existe una comida con ese nombre';
      alert(this.errorMessage);
    }
  }

  openEditPopup(comida: Comida) {
    this.isEditPopupVisible = true;
    this.editableComida = { ...comida };
  }

  closeEditPopup() {
    this.isEditPopupVisible = false;
    this.editableComida = new Comida();
  }

  confirmEdit() {
    if (!this.editableComida.nombre || !this.editableComida.tipo) {
      alert('Por favor completa todos los campos');
      return;
    }

    if (!this.isNameUnique(this.editableComida)) {
      alert('Ya existe una comida con ese nombre');
      return;
    }

    this.comidaService.editarComidas(this.editableComida).subscribe(
      (updatedComida) => {
        const index = this.comidas.findIndex(
          (c) => c.nombre === updatedComida.nombre
        );
        if (index !== -1) {
          this.comidas[index] = updatedComida;
        }
        this.closeEditPopup();
      },
      (error) => {
        alert('Error al actualizar la comida');
        console.error(error);
      }
    );
  }
}
