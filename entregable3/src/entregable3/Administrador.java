package entregable3;

import java.sql.Date;

public class Administrador extends Personal {
	

	public Administrador(String dni, String clave, String nombre, String apellido, String email) {
		super(dni, clave, nombre, apellido, email);
		// TODO Auto-generated constructor stub
	}
	
	public Responsable crearResponsable(String dni, String nombre, String apellido, String email) {
		return null;
	}
	
	public void editarTurno(Responsable r) {
		
	}
	
	public Comida crearComida(String nombre, String tipo, boolean vegetariano) {
		return null;
	}
	
	public Menu crearMenu() {
		return null;
	}
	
	public Carta crearCarta(Date semana) {
		return null;
	}

}
