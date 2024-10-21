package quecomemos.model;
import javax.persistence.*;


import java.sql.Date;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "Responsables")
public class Responsable extends Usuario {

	@Column(nullable = false)
	private String turno;

	public Responsable(String dni, String clave, String nombre, String apellido, String email) {
		super(dni, clave, nombre, apellido, email);
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno){
		this.turno = turno;
	}

	
}
