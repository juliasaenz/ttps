export interface Comida {
    nombre: string;
    tipo: 'ENTRADA' | 'PLATO_PRINCIPAL' | 'BEBIDA' | 'POSTRE';
    vegetariano: boolean;
  }