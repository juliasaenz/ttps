package entregable3;

import java.util.ArrayList;

public class Menu {
	
	ArrayList<Comida> comidas;
	double precio;
	
	public Menu() {
		comidas = new ArrayList<Comida>();
	}

	public ArrayList<Comida> getComidas() {
		return comidas;
	}

	public void setComidas(ArrayList<Comida> comidas) {
		this.comidas = comidas;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public void agregarComida(Comida c) {
		
	}
	
	public void eliminarComida(Comida c) {
		
	}
	
}
