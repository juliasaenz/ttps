import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-comidas',
  imports: [CommonModule, FormsModule],
  templateUrl: './comida.component.html',
  styleUrls: ['./comida.component.css']
})
export class ComidaComponent {
  comidas = [
    { nombre: 'Ensalada César', tipo: 'entrada', vegetariano: true },
    { nombre: 'Milanesa', tipo: 'plato principal', vegetariano: false },
    { nombre: 'Coca-Cola', tipo: 'bebida', vegetariano: true },
    { nombre: 'Helado', tipo: 'postre', vegetariano: true },
    { nombre: 'Ensalada César', tipo: 'entrada', vegetariano: true },
    { nombre: 'Milanesa', tipo: 'plato principal', vegetariano: false },
    { nombre: 'Coca-Cola', tipo: 'bebida', vegetariano: true },
    { nombre: 'Helado', tipo: 'postre', vegetariano: true },
    { nombre: 'Ensalada César', tipo: 'entrada', vegetariano: true },
    { nombre: 'Milanesa', tipo: 'plato principal', vegetariano: false },
    { nombre: 'Coca-Cola', tipo: 'bebida', vegetariano: true },
    { nombre: 'Helado', tipo: 'postre', vegetariano: true }
  ];

  newComida = {
    nombre: '',
    tipo: '',
    vegetariano: false
  };

  addComida() {
    const isNameUnique = !this.comidas.find(
      comida => comida.nombre.toLowerCase() === this.newComida.nombre.toLowerCase()
    );

    if (isNameUnique) {
      this.comidas.push({ ...this.newComida });
      this.newComida = { nombre: '', tipo: '', vegetariano: false };
    } else {
      alert('The name of the comida must be unique!');
    }
  }
}
