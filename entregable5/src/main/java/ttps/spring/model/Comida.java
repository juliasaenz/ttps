package ttps.spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

	protected Comida() {
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