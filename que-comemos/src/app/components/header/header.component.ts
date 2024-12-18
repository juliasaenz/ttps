import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  /* TODO: Hacer esto funcional a partir de JWT y roles*/
  isLoggedIn = true;

  userRole: 'cliente' | 'admin' | 'responsable' = "admin";
}
