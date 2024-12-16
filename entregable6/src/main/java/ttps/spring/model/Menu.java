package ttps.spring.model;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Menu")
@Component
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "Menu_Comida", // Name of the join table
            joinColumns = @JoinColumn(name = "menu_id"), // Foreign key to Menu
            inverseJoinColumns = @JoinColumn(name = "comida_id") // Foreign key to Comida
    )
    private List<Comida> comidas = new ArrayList<>();

    @Column
    private double precio;

    public Menu() {
    }

    public List<Comida> getComidas() {
        return comidas;
    }

    public void setId(Long id) {
		this.id = id;
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