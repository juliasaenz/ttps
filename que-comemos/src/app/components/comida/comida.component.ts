import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Comida } from '../../models/comida.model';
import { ComidaService } from '../../services/comida.service';

@Component({
  selector: 'app-comidas',
  imports: [CommonModule, FormsModule],
  templateUrl: './comida.component.html',
  styleUrls: ['./comida.component.css']
})
  
export class ComidaComponent implements OnInit {
  comidas: Comida[] = [];
  newComida: Comida = {
    nombre: '',
    tipo: null,
    vegetariano: false
  };
  private errorMessage = '';

  constructor(private comidaService: ComidaService) { }

  ngOnInit(): void {
    this.getComidas();
  }

  private getComidas(): void {
    this.comidaService.getComidas().subscribe((data: Comida[]) => {
      this.comidas = data;
    });
  }

  addComida() {
    const isNameUnique = !this.comidas.find(
      comida => comida.nombre.toLowerCase() === this.newComida.nombre.toLowerCase()
    );

    if (isNameUnique) {
      const nombreFormatted = this.newComida.nombre.charAt(0).toUpperCase() + this.newComida.nombre.slice(1).toLowerCase();
      this.newComida.nombre = nombreFormatted;
      this.comidaService.addComidas(this.newComida).subscribe(
        response => {
          this.comidas.push(response);
          this.newComida = { nombre: '', tipo: null, vegetariano: false };
        },
        error => {
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

  //TODO: Hacer funcionar
  editComida(comida: Comida) {
    console.log(comida);
  }
}

