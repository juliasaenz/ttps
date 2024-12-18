export interface Comida {
     id?: number;
    nombre: string;
    tipo: 'ENTRADA' | 'PLATO_PRINCIPAL' | 'BEBIDA' | 'POSTRE' | null;
    vegetariano: boolean;
  }