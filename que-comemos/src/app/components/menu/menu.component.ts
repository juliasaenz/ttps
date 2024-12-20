import { Component, OnDestroy, OnInit } from '@angular/core';
import { Comida } from '../../models/comida.model';
import { Menu } from '../../models/menu.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MenuService } from '../../services/menu.service';
import { ComidaService } from '../../services/comida.service';
import { AuthService } from '../../services/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
})
export class MenuComponent implements OnInit, OnDestroy {
  entradas: Comida[] = [];
  platosPrincipales: Comida[] = [];
  postres: Comida[] = [];
  bebidas: Comida[] = [];
  menus: Menu[] = [];

  isEditPopupVisible = false;
  editableMenu: Menu = new Menu();
  newMenu: Menu = new Menu();

  isLoggedIn = false;
  userRole: 'clientes' | 'administradores' | 'responsables' | null = null;
  private subscriptions: Subscription = new Subscription();

  constructor(
    private menuService: MenuService,
    private comidaService: ComidaService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.subscriptions.add(
      this.authService.isAuthenticated$.subscribe((loggedIn) => {
        this.isLoggedIn = loggedIn;
        if (loggedIn) {
          this.getComidas(); 
          this.getMenus(); 
        } else {
          this.clearData(); 
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

  private clearData(): void {
    this.entradas = [];
    this.platosPrincipales = [];
    this.postres = [];
    this.bebidas = [];
    this.menus = [];
  }

  addMenu(): void {
    if (this.newMenu.platoPrincipal && this.newMenu.precio > 0) {
      this.menuService.addMenu(this.newMenu).subscribe((addedMenu) => {
        this.menus.push(this.parseMenuAdded(addedMenu));
        this.newMenu = new Menu();
      });
    } else {
      alert('El plato principal y el precio son obligatorios.');
    }
  }

  private parseMenuAdded(menu: Menu): Menu {
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

  openEditPopup(menu: Menu): void {
    this.editableMenu = {
      id: menu.id,
      entrada: this.entradas.find((c) => c.id === menu.entrada?.id) || null,
      platoPrincipal:
        this.platosPrincipales.find((c) => c.id === menu.platoPrincipal?.id) ||
        null,
      bebida: this.bebidas.find((c) => c.id === menu.bebida?.id) || null,
      postre: this.postres.find((c) => c.id === menu.postre?.id) || null,
      precio: menu.precio,
      vegetariano: menu.vegetariano,
    };
    this.isEditPopupVisible = true;

    console.log('Editable menu:', this.editableMenu);
  }

  closeEditPopup(): void {
    this.isEditPopupVisible = false;
    this.editableMenu = new Menu();
  }

  confirmEdit(): void {
    if (!this.editableMenu.platoPrincipal || this.editableMenu.precio <= 0) {
      alert('El plato principal y el precio son obligatorios.');
      return;
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
        console.error(error);
        alert('Error al actualizar el menú.');
      }
    );
  }
}
