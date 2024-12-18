import { Comida } from './comida.model';
export interface Menu {
  entrada?: Comida | null;
  platoPrincipal: Comida | null;
  bebida?: Comida | null;
  postre?: Comida | null;
  precio: number;
  vegetariano: boolean;
}