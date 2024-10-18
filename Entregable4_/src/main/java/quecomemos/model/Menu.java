package quecomemos.model;

import java.util.ArrayList;

@Entity
@Table(name = "Compras")
public class Menu {
	
	@OneToMany(mappedBy = "Comida", cascade = CascadeType.PERSIST)
	private ArrayList<Comida> comidas;

	@Column
	private double precio;
	
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
	
	public boolean isVegetariano() {
		return this.comidas.stream().allMatch(comida -> comida.isVegetariano());
	}
	
}
