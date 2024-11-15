package ttps.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Administradores")
public class Administrador extends Usuario {

    public Administrador(String dni, String clave, String nombre, String apellido, String email) {
        super(dni, clave, nombre, apellido, email);
    }

    protected Administrador() {}

}