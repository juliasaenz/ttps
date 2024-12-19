import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Menu } from '../models/menu.model';
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
export class MenuService {
  private apiUrl = 'http://localhost:8080/menus';

  constructor(private http: HttpClient) {}

  getMenus(): Observable<Menu[]> {
    return this.http.get<Menu[]>(this.apiUrl).pipe(
      map((menus) =>
        menus.map((menu) => {
          const entrada =
            menu.comidas?.find((comida) => comida.tipo === 'ENTRADA') || null;
          const platoPrincipal =
            menu.comidas?.find((comida) => comida.tipo === 'PLATO_PRINCIPAL') ||
            null;
          const bebida =
            menu.comidas?.find((comida) => comida.tipo === 'BEBIDA') || null;
          const postre =
            menu.comidas?.find((comida) => comida.tipo === 'POSTRE') || null;

          return {
            id: menu.id,
            entrada: entrada,
            platoPrincipal: platoPrincipal,
            bebida: bebida,
            postre: postre,
            precio: menu.precio,
            vegetariano: menu.vegetariano,
          };
        })
      ),
      catchError(() => {
        return of([]);
      })
    );
  }

  addMenu(menu: Menu): Observable<Menu> {
    const comidas: Comida[] = [];

    if (menu.entrada) comidas.push(menu.entrada);
    if (menu.platoPrincipal) comidas.push(menu.platoPrincipal);
    if (menu.bebida) comidas.push(menu.bebida);
    if (menu.postre) comidas.push(menu.postre);

    const menuToAdd = {
      ...menu,
      comidas: comidas,
    };

    return this.http.post<Menu>(this.apiUrl, menuToAdd, httpOptions).pipe(
      catchError(() => {
        return of(menuToAdd);
      })
    );
  }

  editarMenus(menu: Menu): Observable<Menu> {
    const comidas: Comida[] = [];

    if (menu.entrada) comidas.push(menu.entrada);
    if (menu.platoPrincipal) comidas.push(menu.platoPrincipal);
    if (menu.bebida) comidas.push(menu.bebida);
    if (menu.postre) comidas.push(menu.postre);

    const menuToEdit = {
      ...menu,
      comidas: comidas,
    };

    return this.http
      .put<Menu>(`${this.apiUrl}/${menu.id}`, menuToEdit, httpOptions)
      .pipe(
        catchError(() => {
          return of(menuToEdit);
        })
      );
  }


}
