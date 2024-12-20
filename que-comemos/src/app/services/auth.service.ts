import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly apiUrl = 'http://localhost:8080';
  
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);
  private userRoleSubject = new BehaviorSubject<'clientes' | 'responsables' | 'administradores' | null>(null);

  isAuthenticated$: Observable<boolean> = this.isAuthenticatedSubject.asObservable();
  userRole$: Observable<'clientes' | 'responsables' | 'administradores' | null> = this.userRoleSubject.asObservable();

  constructor(private http: HttpClient, private router: Router) {
    const storedRole = localStorage.getItem('rol') as 'clientes' | 'responsables' | 'administradores' | null;
    const token = localStorage.getItem('jwt');
    this.isAuthenticatedSubject.next(!!token);
    this.userRoleSubject.next(storedRole);
  }

  login(credentials: { email: string; password: string }, role: 'clientes' | 'responsables' | 'administradores'): Observable<{ token: string }> {
    const endpoint = `${this.apiUrl}/${role}/login`;
    return this.http.post<{ token: string }>(endpoint, credentials).pipe(
      tap((response) => {
        console.log(response);
        localStorage.setItem('jwt', response.token);
        localStorage.setItem('rol', role);

        this.isAuthenticatedSubject.next(true);
        this.userRoleSubject.next(role);
      })
    );
  }

  logout(): void {
    localStorage.removeItem('jwt');
    localStorage.removeItem('rol');

    this.isAuthenticatedSubject.next(false);
    this.userRoleSubject.next(null);

    this.router.navigate(['/login']);
  }

  isAuthenticated(): boolean {
    return this.isAuthenticatedSubject.value;
  }

  getUserRole(): 'clientes' | 'responsables' | 'administradores' | null {
    return this.userRoleSubject.value;
  }
}
