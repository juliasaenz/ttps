package quecomemos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Comidas")
public class Comida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoComida tipo;

    @Column(nullable = false)
    private boolean vegetariano;

    public Comida(String nombre, TipoComida tipo, boolean vegetariano) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.vegetariano = vegetariano;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoComida getTipo() {
        return tipo;
    }

    public boolean isVegetariano() {
        return vegetariano;
    }

	public Long getId() {
		return this.id;
	}
}
