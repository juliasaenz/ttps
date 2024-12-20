import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Comida } from '../../models/comida.model';
import { ComidaService } from '../../services/comida.service';

@Component({
  selector: 'app-comidas',
  imports: [CommonModule, FormsModule],
  templateUrl: './comida.component.html',
  styleUrls: ['./comida.component.css'],
})
export class ComidaComponent implements OnInit {
  comidas: Comida[] = [];
  newComida: Comida = new Comida();
  private errorMessage = '';

  isEditPopupVisible = false;
  editableComida: Comida = new Comida();

  constructor(private comidaService: ComidaService) {}

  ngOnInit(): void {
    this.getComidas();
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
