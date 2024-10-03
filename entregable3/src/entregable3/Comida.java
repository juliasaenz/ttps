package entregable3;

public class Comida {
	
	String nombre;
	String tipo;
	boolean vegetariano;
	
	public Comida(String nombre, String tipo, boolean vegetariano) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.vegetariano = vegetariano;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public boolean isVegetariano() {
		return vegetariano;
	}
	
	

}
