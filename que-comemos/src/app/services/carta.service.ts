import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Carta } from '../models/carta.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: 'my-auth-token',
  }),
};

@Injectable({
  providedIn: 'root',
})
export class CartaService {
  private apiUrl = 'http://localhost:8080/cartas';

  constructor(private http: HttpClient) {}

  getCartas(): Observable<Carta[]> {
    return this.http.get<Carta[]>(this.apiUrl).pipe(
      catchError(() => {
        return of([]);
      })
    );
  }

  addCartas(carta: Carta): Observable<Carta> {
    return this.http.post<Carta>(this.apiUrl, carta, httpOptions).pipe(
      catchError(() => {
        return of(carta);
      })
    );
  }

  editarCartas(carta: Carta): Observable<Carta> {
    return this.http.put<Carta>(`${this.apiUrl}/${carta.id}`, carta, httpOptions).pipe(
      catchError(() => {
        return of(carta);
      })
    );
  }
}

