import { Component, OnInit } from '@angular/core';
import { Comida } from '../../models/comida.model';
import { Menu } from '../../models/menu.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MenuService } from '../../services/menu.service';
import { ComidaService } from '../../services/comida.service';

@Component({
  selector: 'app-menu',
  imports: [CommonModule, FormsModule],
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
})
export class MenuComponent implements OnInit {
  //TODO: traer las comidas de la api
  comidas: Comida[] = [];
  comidasEntradas: Comida[] = [];
  comidasPlatosPrincipales: Comida[] = [];
  comidasPostres: Comida[] = [];
  comidasBebidas: Comida[] = [];
  menus: Menu[] = [];

  // Nuevo menú
  newMenu: Menu = {
    entrada: null,
    platoPrincipal: null,
    bebida: null,
    postre: null,
    precio: 0,
    vegetariano: false,
  };

  constructor(
    private menuService: MenuService,
    private comidaService: ComidaService
  ) {}

  ngOnInit(): void {
    this.getComidas();
    this.getMenus();
  }

  private getComidas(): void {
    this.comidaService.getComidas().subscribe((data: Comida[]) => {
      this.comidas = data;

      console.log('Comidas:', this.comidas);

      this.comidasEntradas = this.comidas.filter((c) => c.tipo === 'ENTRADA');
      this.comidasPlatosPrincipales = this.comidas.filter(
        (c) => c.tipo === 'PLATO_PRINCIPAL'
      );
      this.comidasPostres = this.comidas.filter((c) => c.tipo === 'POSTRE');
      this.comidasBebidas = this.comidas.filter((c) => c.tipo === 'BEBIDA');
    });
  }

  private getMenus(): void {
    this.menuService.getMenus().subscribe((data: Menu[]) => {
      this.menus = data;
    });
  }

  addMenu() {
    if (this.newMenu.platoPrincipal && this.newMenu.precio > 0) {
      this.menuService.addMenu(this.newMenu).subscribe((addedMenu) => {
        console.log('Menu added:', addedMenu);
        this.menus.push(this.parseMenuAdded(addedMenu));
        this.resetNewMenu();
      });
    } else {
      alert('El plato principal y el precio son obligatorios.');
    }
  }

  private parseMenuAdded(menu: Menu) {
    return {
      id: menu.id,
      entrada: menu.entrada ?? null,
      platoPrincipal: menu.comidas?.find((c) => c.tipo === 'PLATO_PRINCIPAL') ?? null,
      bebida: menu.comidas?.find((c) => c.tipo === 'BEBIDA') ?? null,
      postre: menu.comidas?.find((c) => c.tipo === 'POSTRE') ?? null,
      precio: menu.precio,
      vegetariano: menu.vegetariano,
    };
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
      vegetariano: false,
    };
  }
}
