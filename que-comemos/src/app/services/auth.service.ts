import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly apiUrl = 'http://localhost:8080/clientes/login';

  constructor(private http: HttpClient, private router: Router) {}

  login(credentials: { email: string; password: string }): Observable<{ token: string }> {
    return this.http.post<{ token: string }>(this.apiUrl, credentials).pipe(
      tap((response) => {
        localStorage.setItem('jwt', response.token); // Guarda el token JWT en el localStorage
      })
    );
  }

  

  logout(): void {
    localStorage.removeItem('jwt');
    this.router.navigate(['/login']);
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('jwt');
  }
}
