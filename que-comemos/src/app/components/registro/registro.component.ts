import { Component } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-registro',
  imports: [],
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.css'
})
export class RegistroComponent {

  constructor(private router: Router) { }
  
  goToCliente() {
    this.router.navigate(['/registro/cliente']);
  }

  goToResponsable() { 
    this.router.navigate(['/registro/responsable']);
  }

  goToAdmin() {
    this.router.navigate(['/registro/admin']);
  }

}
