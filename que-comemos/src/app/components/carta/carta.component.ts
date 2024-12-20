import { Component, OnInit, OnDestroy } from '@angular/core';
import { Carta } from '../../models/carta.model';
import { Menu } from '../../models/menu.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MenuService } from '../../services/menu.service';
import { CartaService } from '../../services/carta.service';
import { AuthService } from '../../services/auth.service';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-carta',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './carta.component.html',
  styleUrls: ['./carta.component.css'],
})
export class CartaComponent implements OnInit, OnDestroy {
  today = new Date().toISOString().split('T')[0];
  cartas: Carta[] = [];
  menus: Menu[] = [];
  parsedMenus: { id: number; nombre: string }[] = [];
  parsedMenusVeggie: { id: number; nombre: string }[] = [];

  isLoggedIn = false;
  userRole: 'clientes' | 'administradores' | 'responsables' | null = null;
  newCarta: Carta = new Carta();
  editableCarta: Carta = new Carta();
  isEditPopupVisible = false;

  private subscriptions: Subscription = new Subscription();

  constructor(
    private router: Router,
    private menuService: MenuService,
    private cartaService: CartaService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.subscriptions.add(
      this.authService.isAuthenticated$.subscribe((loggedIn) => {
        this.isLoggedIn = loggedIn;
        if (loggedIn) {
          this.loadMenus();
          this.loadCartas();
        } else {
          this.clearData();
        }
      })
    );

    this.subscriptions.add(
      this.authService.userRole$.subscribe((role) => {
        this.userRole = role;
        console.log('Rol del usuario actualizado:', role);
        if(role === 'clientes'){
          this.router.navigate(['/']);
        }
      })
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

  addCarta(): void {
    const formattedDate = this.formatDate(this.newCarta.dia);
    if (this.cartaExists(formattedDate, -1)) {
      alert('Ya existe una carta para esta fecha.');
      return;
    }

    if (this.newCarta.dia && this.newCarta.menu && this.newCarta.menuVeggie) {
      if (!this.assignMenusToCarta()) {
        alert('No se encontraron los menús seleccionados.');
        return;
      }
      this.cartaService.addCartas(this.newCarta).subscribe((carta) => {
        this.cartas.push(carta);
        this.sortCartasByDate();
        this.resetNewCarta();
      });
    } else {
      alert('Todos los campos son obligatorios.');
    }
  }

  resetNewCarta(): void {
    this.newCarta = new Carta();
  }

  loadMenus(): void {
    this.menuService.getMenus().subscribe((data: Menu[]) => {
      this.menus = data;
      this.parsedMenus = this.formatMenuOptions(this.menus);
      this.parsedMenusVeggie = this.formatMenuOptions(
        this.menus.filter((menu) => menu.vegetariano)
      );
    });
  }

  loadCartas(): void {
    this.cartaService.getCartas().subscribe((data: Carta[]) => {
      this.cartas = data;
    });
  }

  openEditPopup(carta: Carta): void {
    this.editableCarta = { ...carta };
    this.isEditPopupVisible = true;
  }

  closeEditPopup(): void {
    this.isEditPopupVisible = false;
    this.editableCarta = new Carta();
  }

  confirmEdit(): void {
    const formattedDate = this.formatDate(this.editableCarta.dia);
    if (this.cartaExists(formattedDate, this.editableCarta.id ?? -1)) {
      alert('Ya existe una carta para esta fecha.');
      return;
    }

    if (this.editableCarta.dia && this.editableCarta.menu && this.editableCarta.menuVeggie) {
      if (!this.assignMenusToEditableCarta()) {
        alert('No se encontraron los menús seleccionados.');
        return;
      }
      this.cartaService.editarCartas(this.editableCarta).subscribe(() => {
        this.loadCartas();
        this.closeEditPopup();
      });
    } else {
      alert('Todos los campos son obligatorios.');
    }
  }

  private formatDate(date: Date): string {
    return new Date(date).toISOString().split('T')[0];
  }

  private cartaExists(formattedDate: string, id: number): boolean {
    return this.cartas.some((carta) => {
      const cartaDate = this.formatDate(carta.dia);
      return cartaDate === formattedDate && carta.id !== id;
    });
  }

  private assignMenusToCarta(): boolean {
    this.newCarta.menu = this.menus.find(
      (menu) => menu.id === this.newCarta.menu?.id
    ) ?? null;
    this.newCarta.menuVeggie = this.menus.find(
      (menu) => menu.id === this.newCarta.menuVeggie?.id
    ) ?? null;
    return this.newCarta.menu !== null && this.newCarta.menuVeggie !== null;
  }

  private assignMenusToEditableCarta(): boolean {
    this.editableCarta.menu = this.menus.find(
      (menu) => menu.id === this.editableCarta.menu?.id
    ) ?? null;
    this.editableCarta.menuVeggie = this.menus.find(
      (menu) => menu.id === this.editableCarta.menuVeggie?.id
    ) ?? null;
    return this.editableCarta.menu !== null && this.editableCarta.menuVeggie !== null;
  }

  private sortCartasByDate(): void {
    this.cartas.sort((a, b) => a.dia.getTime() - b.dia.getTime());
  }

  private formatMenuOptions(menus: Menu[]): { id: number; nombre: string }[] {
    return menus.map((menu) => ({
      id: menu.id ?? 0,
      nombre: `${menu.platoPrincipal?.nombre} (${[menu.entrada?.nombre, menu.bebida?.nombre, menu.postre?.nombre]
        .filter((nombre) => nombre !== undefined)
        .join(', ')}) - $ ${menu.precio}`,
    }));
  }

  private clearData(): void {
    this.cartas = [];
    this.menus = [];
    this.parsedMenus = [];
    this.parsedMenusVeggie = [];
  }
}
