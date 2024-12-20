import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Carta } from '../../models/carta.model';
import { CartaService } from '../../services/carta.service';

@Component({
  selector: 'app-home',
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  constructor(private router: Router, private cartaService: CartaService) {
    this.loadCartas(); //TODO: Mover a cuando cambie loggedIn
  }

  // TODO: Hacer que esto sea funcional
  isLoggedIn = true;
  userRole: 'clientes' | 'administradores' | 'responsables' = 'clientes';
  // TODO: Add check of user roles

  today = new Date().toISOString().split('T')[0];
  cartas: Carta[] = [];

  goToRegistro() {
    this.router.navigate(['/registro']);
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }

  loadCartas(): void {
    this.cartaService.getCartas().subscribe((data: Carta[]) => {
      this.cartas = data;
    });
  }
}
