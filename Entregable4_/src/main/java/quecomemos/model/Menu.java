package quecomemos.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Menu")
public class Menu {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "Menu_Comida", // Name of the join table
        joinColumns = @JoinColumn(name = "menu_id"), // Foreign key to Menu
        inverseJoinColumns = @JoinColumn(name = "comida_id") // Foreign key to Comida
    )
	private List<Comida> comidas;

	@Column
	private double precio;

	public Menu() {
	}

	public List<Comida> getComidas() {
		return comidas;
	}

	public void setComidas(List<Comida> comidas) {
		this.comidas = comidas;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean isVegetariano() {
		return this.comidas.stream().allMatch(comida -> comida.isVegetariano());
	}

	public Long getId() {
		return id;
	}

}
