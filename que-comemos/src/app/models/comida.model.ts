export class Comida {
  id?: number;
  nombre: string;
  tipo: 'ENTRADA' | 'PLATO_PRINCIPAL' | 'BEBIDA' | 'POSTRE' | null;
  vegetariano: boolean;
  
  constructor() {
    this.nombre = '';
    this.tipo = null;
    this.vegetariano = false;
  }
}