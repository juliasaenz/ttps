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
	private Carta carta;

	public Compra(Cliente cliente, Date fecha, Carta carta) {
		super();
		this.cliente = cliente;
		this.fecha = fecha;
		this.carta = carta;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public Date getFechaCompra() {
		return fecha;
	}
	
	public Date getFechaMenu() {
		return carta.getDia();
	}
	public Menu getMenu() {
		return carta.getMenu();
	}
	public Long getId() {
		return id;
	}
	
	
	
	

}
