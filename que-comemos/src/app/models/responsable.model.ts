export class Responsable {
    constructor() {
        this.nombre = '';
        this.apellido = '';
        this.email = '';
        this.dni = '';
        this.clave = '';
        this.turno = 'tarde';   
     }
    id?: number;
    apellido: string;
    nombre: string;
    dni: string;
    email: string;
    clave: string;
    turno: string;
}