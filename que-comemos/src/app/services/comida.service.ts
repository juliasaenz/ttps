import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Comida } from '../models/comida.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: 'my-auth-token',
  }),
};

@Injectable({
  providedIn: 'root',
})
export class ComidaService {
  private apiUrl = 'http://localhost:8080/comidas';

  constructor(private http: HttpClient) {}

  getComidas(): Observable<Comida[]> {
    return this.http.get<Comida[]>(this.apiUrl).pipe(
      catchError(() => {
        return of([]);
      })
    );
  }

  addComidas(comida: Comida): Observable<Comida> {
    return this.http.post<Comida>(this.apiUrl, comida, httpOptions).pipe(
      catchError(() => {
        return of(comida);
      })
    );
  }

  editarComidas(comida: Comida): Observable<Comida> {
    return this.http.put<Comida>(`${this.apiUrl}/${comida.id}`, comida, httpOptions).pipe(
      catchError(() => {
        return of(comida);
      })
    );
  }

}

