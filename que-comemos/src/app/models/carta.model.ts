import { Menu } from './menu.model';
export interface Carta {
    id?: number;
    menu: Menu | null;
    menuVeggie: Menu | null;
    dia: Date;
}