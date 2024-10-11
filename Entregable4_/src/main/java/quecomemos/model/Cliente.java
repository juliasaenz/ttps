package quecomemos.model;

import java.sql.Date;
import java.util.ArrayList;

public class Cliente extends Usuario {
	
	ArrayList<Compra> compras;
	boolean vegetariano = false;
	
	public Cliente(String dni, String clave, String nombre, String apellido, String email) {
		super(dni, clave, nombre, apellido, email);
		compras = new ArrayList<Compra>();
	}

	public boolean isVegetariano() {
		return vegetariano;
	}

	public void setVegetariano(boolean vegetariano) {
		this.vegetariano = vegetariano;
	}

	public ArrayList<Compra> getCompras() {
		return compras;
	}
	
	public ArrayList<Compra> getComprasDia(Date fecha) {
		return compras;
	}
	
	public Compra comprarMenu(Date fecha, Menu menu) {
		return null;
	}
	
	public Sugerencia crearSugerencia(String texto, String tipo) {
		return null;
	}
	
	
	
}
