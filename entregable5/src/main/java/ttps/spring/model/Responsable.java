package ttps.spring.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "Responsables")
@Component
public class Responsable extends Usuario {

    @Column(nullable = false)
    private String turno;

    public Responsable(String dni, String clave, String nombre, String apellido, String email) {
        super(dni, clave, nombre, apellido, email);
    }

    protected Responsable() {}

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno){
        this.turno = turno;
    }


}