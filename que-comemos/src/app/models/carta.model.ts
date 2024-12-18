import { Menu } from './menu.model';
export interface Carta {
    menu: Menu | null;
    menuVeggie: Menu | null;
    dia: Date;
}