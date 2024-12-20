import { Component, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Carta } from '../../models/carta.model';
import { CartaService } from '../../services/carta.service';
import { AuthService } from '../../services/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnDestroy {
  isLoggedIn = false;
  userRole: 'clientes' | 'administradores' | 'responsables' | null = null;
  today = new Date().toISOString().split('T')[0];
  cartas: Carta[] = [];
  private subscriptions: Subscription = new Subscription();

  constructor(
    private router: Router,
    private cartaService: CartaService,
    private authService: AuthService
  ) {
    this.subscriptions.add(
      this.authService.isAuthenticated$.subscribe((loggedIn) => {
        this.isLoggedIn = loggedIn;
        if (loggedIn) {
          this.loadCartas(); 
        } else {
          this.cartas = []; 
        }
      })
    );

    this.subscriptions.add(
      this.authService.userRole$.subscribe((role) => {
        this.userRole = role;
        console.log('User role changed:', role);
      })
    );
  }

  goToRegistro() {
    this.router.navigate(['/registro']);
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }

  loadCartas(): void {
    if (this.isLoggedIn) {
      this.cartaService.getCartas().subscribe((data: Carta[]) => {
        this.cartas = data;
      });
    }
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
}
