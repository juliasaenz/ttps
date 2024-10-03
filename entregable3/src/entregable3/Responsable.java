package entregable3;

import java.sql.Date;
import java.util.ArrayList;

public class Responsable extends Personal {
	String turno;

	public Responsable(String dni, String clave, String nombre, String apellido, String email) {
		super(dni, clave, nombre, apellido, email);
	}

	public String getTurno() {
		return turno;
	}

	
}
