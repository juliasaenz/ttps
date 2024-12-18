import { Component } from '@angular/core';
import { Carta } from '../../models/carta.model';
import { Menu } from '../../models/menu.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-carta',
  imports: [CommonModule, FormsModule],
  templateUrl: './carta.component.html',
  styleUrls: ['./carta.component.css'],
})
export class CartaComponent {
  cartas: Carta[] = [];

  //TODO: traer menus de api
  menus: Menu[] = [
    {
      entrada: { nombre: 'Ensalada', tipo: 'ENTRADA', vegetariano: true },
      platoPrincipal: { nombre: 'Risotto', tipo: 'PLATO_PRINCIPAL', vegetariano: true },
      bebida: { nombre: 'Jugo de Naranja', tipo: 'BEBIDA', vegetariano: true },
      postre: { nombre: 'Helado', tipo: 'POSTRE', vegetariano: true },
      precio: 800,
      vegetariano: true,
    },
    {
      entrada: { nombre: 'Sopa', tipo: 'ENTRADA', vegetariano: false },
      platoPrincipal: { nombre: 'Asado', tipo: 'PLATO_PRINCIPAL', vegetariano: false },
      bebida: { nombre: 'Gaseosa', tipo: 'BEBIDA', vegetariano: true },
      postre: { nombre: 'Flan', tipo: 'POSTRE', vegetariano: true },
      precio: 1200,
      vegetariano: false,
    },
  ];

  newCarta: Carta = {
    dia: new Date(),
    menu:  null,
    menuVeggie: null
  };

  //TODO: Con api
  addCarta() {
    const existingCarta = this.cartas.find((carta) => carta.dia === this.newCarta.dia);
    if (existingCarta) {
      alert('Ya existe una carta para esta fecha.');
      return;
    }

    if (this.newCarta.dia && this.newCarta.menu && this.newCarta.menuVeggie) {
      this.cartas.push({ ...this.newCarta });
      this.cartas.sort((a, b) => a.dia.getTime() - b.dia.getTime());
      this.resetNewCarta();
    } else {
      alert('Todos los campos son obligatorios.');
    }
  }

  //TODO: Hacer funcional
  editCarta(carta: Carta) {
    console.log(carta);
  }

  resetNewCarta() {
    this.newCarta = {
      dia: new Date(),
      menu: null,
      menuVeggie: null,
    };
  }
}
