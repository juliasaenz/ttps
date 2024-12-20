import { Menu } from './menu.model';
export class Carta {
    id?: number;
    menu: Menu | null;
    menuVeggie: Menu | null;
    dia: Date;

    constructor() {
        this.menu = null;
        this.menuVeggie = null;
        this.dia = new Date();
    }
}