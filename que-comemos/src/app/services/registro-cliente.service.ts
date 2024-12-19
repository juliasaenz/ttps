import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Cliente } from '../models/cliente.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: 'my-auth-token',
  }),
};

@Injectable({
  providedIn: 'root',
})
export class RegistroClienteService {
  private apiUrl = 'http://localhost:8080/clientes/registrar';

  constructor(private http: HttpClient) {}

  getClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(this.apiUrl).pipe(
      catchError(() => {
        return of([]);
      })
    );
  }

  registrarCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(this.apiUrl, cliente, httpOptions).pipe(
      catchError(() => {
        return of(cliente);
      })
    );
  }

  editarCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.put<Cliente>(`${this.apiUrl}/${cliente.id}`, cliente, httpOptions).pipe(
      catchError(() => {
        return of(cliente);
      })
    );
  }

}

