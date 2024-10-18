package quecomemos.model;

import java.sql.Date;
import java.util.ArrayList;

@Entity
@Table(name = "Responsables")
public class Responsable extends Responsable {

	@Column(nullable = false)
	String turno;

	public Responsable(String dni, String clave, String nombre, String apellido, String email) {
		super(dni, clave, nombre, apellido, email);
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(){
		this.turno = turno;
	}

	
}
