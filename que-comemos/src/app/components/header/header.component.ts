import { Component, Inject, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnDestroy {
  isLoggedIn = false;
  userRole: 'clientes' | 'responsables' | 'administradores' | null = null;
  private subscriptions: Subscription = new Subscription();

  constructor(@Inject(AuthService) private authService: AuthService) {

    this.subscriptions.add(
      this.authService.isAuthenticated$.subscribe((loggedIn) => {
        this.isLoggedIn = loggedIn;
      })
    );

    this.subscriptions.add(
      this.authService.userRole$.subscribe((role) => {
        this.userRole = role;
      })
    );
  }

  logout(): void {
    this.authService.logout();
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe(); 
  }
}
