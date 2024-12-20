import { Comida } from './comida.model';

export class Menu {
  id?: number;
  entrada?: Comida | null;
  platoPrincipal: Comida | null;
  bebida?: Comida | null;
  postre?: Comida | null;
  comidas?: Comida[];
  precio: number;
  vegetariano?: boolean;

  constructor() {
    this.entrada = null;
    this.platoPrincipal = null;
    this.bebida = null;
    this.postre = null;
    this.comidas = [];
    this.precio = 0;
  }
}
