import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Admin } from '../models/admin.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: 'my-auth-token',
  }),
};

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private apiUrl = 'http://localhost:8080/administradores/registrar';

  constructor(private http: HttpClient) {}

  getAdmins(): Observable<Admin[]> {
    return this.http.get<Admin[]>(this.apiUrl).pipe(
      catchError(() => {
        return of([]);
      })
    );
  }

  registrarAdmin(admin: Admin): Observable<Admin> {
    return this.http.post<Admin>(this.apiUrl, admin, httpOptions).pipe(
      catchError(() => {
        return of(admin);
      })
    );
  }

  editarAdmin(admin: Admin): Observable<Admin> {
    return this.http.put<Admin>(`${this.apiUrl}/${admin.id}`, admin, httpOptions).pipe(
      catchError(() => {
        return of(admin);
      })
    );
  }

}

