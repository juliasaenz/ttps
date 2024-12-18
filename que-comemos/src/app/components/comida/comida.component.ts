import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Comida } from '../../models/comida.model';

@Component({
  selector: 'app-comidas',
  imports: [CommonModule, FormsModule],
  templateUrl: './comida.component.html',
  styleUrls: ['./comida.component.css']
})
export class ComidaComponent {
  comidas: Comida[] = [
    { nombre: 'Ensalada', tipo: 'ENTRADA', vegetariano: true },
    { nombre: 'Sopa', tipo: 'ENTRADA', vegetariano: false },
    { nombre: 'Asado', tipo: 'PLATO_PRINCIPAL', vegetariano: false },
    { nombre: 'Risotto', tipo: 'PLATO_PRINCIPAL', vegetariano: true },
    { nombre: 'Jugo de Naranja', tipo: 'BEBIDA', vegetariano: true },
    { nombre: 'Gaseosa', tipo: 'BEBIDA', vegetariano: true },
    { nombre: 'Flan', tipo: 'POSTRE', vegetariano: true },
    { nombre: 'Helado', tipo: 'POSTRE', vegetariano: true },
  ];

  newComida: Comida = {
    nombre: '',
    tipo: null,
    vegetariano: false
  };

  //TODO: Hacer funcionar con API
  addComida() {
    const isNameUnique = !this.comidas.find(
      comida => comida.nombre.toLowerCase() === this.newComida.nombre.toLowerCase()
    );

    if (isNameUnique) {
      this.comidas.push({ ...this.newComida });
      this.newComida = { nombre: '', tipo: null, vegetariano: false };
    } else {
      alert('The name of the comida must be unique!');
    }
  }

  //TODO: hacer funcionar con api
  editComida(comida: Comida) {
    console.log(comida);
  }
}
