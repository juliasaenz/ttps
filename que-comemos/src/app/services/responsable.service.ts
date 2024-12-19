import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Responsable } from '../models/responsable.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: 'my-auth-token',
  }),
};

@Injectable({
  providedIn: 'root',
})
export class ResponsableService {
  private apiUrl = 'http://localhost:8080/responsables/registrar';

  constructor(private http: HttpClient) {}

  getResponsables(): Observable<Responsable[]> {
    return this.http.get<Responsable[]>(this.apiUrl).pipe(
      catchError(() => {
        return of([]);
      })
    );
  }

  registrarResponsable(responsable: Responsable): Observable<Responsable> {
    return this.http.post<Responsable>(this.apiUrl, responsable, httpOptions).pipe(
      catchError(() => {
        return of(responsable);
      })
    );
  }

  editarResponsable(responsable: Responsable): Observable<Responsable> {
    return this.http.put<Responsable>(`${this.apiUrl}/${responsable.id}`, responsable, httpOptions).pipe(
      catchError(() => {
        return of(responsable);
      })
    );
  }

}

