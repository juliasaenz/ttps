package quecomemos.model;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Compras")
public class Compra {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@Column(nullable = false)
	private Date fecha;

	@OneToOne
	@JoinColumn
	private Menu menu;

	public Compra(Cliente cliente, Date fecha, Menu menu) {
		super();
		this.cliente = cliente;
		this.fecha = fecha;
		this.menu = menu;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public Date getFecha() {
		return fecha;
	}
	public Menu getMenu() {
		return menu;
	}
	public Long getId() {
		return id;
	}
	
	
	
	

}
