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
  entradas: Comida[] = [];
  platosPrincipales: Comida[] = [];
  postres: Comida[] = [];
  bebidas: Comida[] = [];
  menus: Menu[] = [];

  isEditPopupVisible = false;

  editableMenu: Menu = new Menu();
  newMenu: Menu = new Menu();

  userRole: 'clientes' | 'administradores' | 'responsables' = 'clientes';
  // TODO: Add check of user roles

  constructor(
    private menuService: MenuService,
    private comidaService: ComidaService
  ) { }

  ngOnInit(): void {
    this.getComidas();
    this.getMenus();
  }

  private getComidas(): void {
    this.comidaService.getComidas().subscribe((data: Comida[]) => {
      this.entradas = data.filter((c) => c.tipo === 'ENTRADA');
      this.platosPrincipales = data.filter((c) => c.tipo === 'PLATO_PRINCIPAL');
      this.postres = data.filter((c) => c.tipo === 'POSTRE');
      this.bebidas = data.filter((c) => c.tipo === 'BEBIDA');
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
        this.menus.push(this.parseMenuAdded(addedMenu));
        this.newMenu = new Menu();
      });
    } else {
      alert('El plato principal y el precio son obligatorios.');
    }
  }

  private parseMenuAdded(menu: Menu) {
    return {
      id: menu.id,
      entrada: menu.entrada ?? null,
      platoPrincipal:
        menu.comidas?.find((c) => c.tipo === 'PLATO_PRINCIPAL') ?? null,
      bebida: menu.comidas?.find((c) => c.tipo === 'BEBIDA') ?? null,
      postre: menu.comidas?.find((c) => c.tipo === 'POSTRE') ?? null,
      precio: menu.precio,
      vegetariano: menu.vegetariano,
    };
  }

  openEditPopup(menu: Menu) {
    this.editableMenu = {
      id: menu.id,
      entrada: this.entradas.find(c => c.id === menu.entrada?.id) || null,
      platoPrincipal: this.platosPrincipales.find(c => c.id === menu.platoPrincipal?.id) || null,
      bebida: this.bebidas.find(c => c.id === menu.bebida?.id) || null,
      postre: this.postres.find(c => c.id === menu.postre?.id) || null,
      precio: menu.precio,
      vegetariano: menu.vegetariano
    };
    this.isEditPopupVisible = true;

    console.log("Editable menu:", this.editableMenu);
  }

  closeEditPopup() {
    this.isEditPopupVisible = false;
    this.editableMenu = new Menu();
  }

  confirmEdit() {
    if (!this.editableMenu.platoPrincipal && this.editableMenu.precio > 0) {
      alert('El plato principal y el precio son obligatorios.');
    }

    this.menuService.editarMenus(this.editableMenu).subscribe(
      (updatedMenu) => {
        const index = this.menus.findIndex((c) => c.id === updatedMenu.id);
        if (index !== -1) {
          this.menus[index] = this.parseMenuAdded(updatedMenu);
        }
        this.closeEditPopup();
      },
      (error) => {
        console.log(error);
        alert('Error al actualizar el menu');
      }
    );
  }
}