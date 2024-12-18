import { Component } from '@angular/core';
import { Comida } from '../../models/comida.model';
import { Menu } from '../../models/menu.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-menu',
  imports: [CommonModule, FormsModule],
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
})
export class MenuComponent {
  //TODO: traer las comidas de la api
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

  // Filtrar comidas por tipo
  comidasEntradas = this.comidas.filter((c) => c.tipo === 'ENTRADA');
  comidasPlatosPrincipales = this.comidas.filter((c) => c.tipo === 'PLATO_PRINCIPAL');
  comidasBebidas = this.comidas.filter((c) => c.tipo === 'BEBIDA');
  comidasPostres = this.comidas.filter((c) => c.tipo === 'POSTRE');

  //TODO: Traer menus con API
  menus: Menu[] = [];

  // Nuevo menú
  newMenu: Menu = {
    entrada: null,
    platoPrincipal: null,
    bebida: null,
    postre: null,
    precio: 0,
    vegetariano: false
  };

  //TODO: pasar por API
  addMenu() {
    if (this.newMenu.platoPrincipal && this.newMenu.precio > 0) {
      this.menus.push({ ...this.newMenu });
      this.resetNewMenu();
    } else {
      alert('El plato principal y el precio son obligatorios.');
    }
  }

  //TODO: Hacer el editar
  editMenu(menu: Menu) {
    console.log(menu);
    // Lógica para editar el menú
  }

  // Resetar formulario
  resetNewMenu() {
    this.newMenu = {
      entrada: null,
      platoPrincipal: null,
      bebida: null,
      postre: null,
      precio: 0,
      vegetariano: false
    };
  }
}
