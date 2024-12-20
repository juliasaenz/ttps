import { Component, 
  Inject
 } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  isLoggedIn = true;
  userRole: 'clientes' | 'administradores' | 'responsables';

  //TODO: Cambiar para que el AuthService tenga un IsLoggedIn y un getUserRole que se updatean
  constructor(@Inject(AuthService) private authService: AuthService) {
    console.log("header");
    const storedRole = localStorage.getItem('rol');
    if (storedRole === 'clientes' || storedRole === 'administradores' || storedRole === 'responsables') {
      this.userRole = storedRole;
    } else {
      this.userRole = 'administradores'; // default
    }
  }

  logout(): void {
    this.authService.logout();
  }

}

