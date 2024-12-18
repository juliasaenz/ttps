import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Menu } from '../models/menu.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: 'my-auth-token',
  }),
};

@Injectable({
  providedIn: 'root',
})
export class MenuService {
  private apiUrl = 'http://localhost:8080/menus';

  constructor(private http: HttpClient) {}

  getMenus(): Observable<Menu[]> {
    return this.http.get<Menu[]>(this.apiUrl).pipe(
      catchError(() => {
        return of([]);
      })
    );
  }

  addMenus(menu: Menu): Observable<Menu> {
    return this.http.post<Menu>(this.apiUrl, menu, httpOptions).pipe(
      catchError(() => {
        return of(menu);
      })
    );
  }

  editarMenus(menu: Menu): Observable<Menu> {
    return this.http.put<Menu>(`${this.apiUrl}/${menu.id}`, menu, httpOptions).pipe(
      catchError(() => {
        return of(menu);
      })
    );
  }
}

