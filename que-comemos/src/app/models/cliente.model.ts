export class Cliente {
    constructor() {
        this.nombre = '';
        this.apellido = '';
        this.email = '';
        this.dni = '';
        this.clave = '';
        this.vegetariano = false;   
     }
    id?: number;
    apellido: string;
    nombre: string;
    dni: string;
    email: string;
    clave: string;
    vegetariano: boolean;
}