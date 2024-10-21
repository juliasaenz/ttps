package quecomemos.model;

import java.sql.Date;
import javax.persistence.*;

import quecomemos.util.TipoSugerencia;

@Entity
@Table(name = "sugerencias")
public class Sugerencia {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Enumerated(EnumType.STRING)
    @Column(nullable = false)
	private TipoSugerencia tipo;
	
	@Column(nullable = false)
	private String texto;
	
	@Column(nullable = false)
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
	public Sugerencia(TipoSugerencia tipo, String texto, Cliente cliente) {
		this.tipo = tipo;
		this.texto = texto;
		this.cliente = cliente;
		this.fecha = new Date(System.currentTimeMillis());
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Sugerencia [tipo=" + tipo + ", texto=" + texto + ", fecha=" + fecha + ", cliente=" + cliente + "]";
	}

	public TipoSugerencia getTipo() {
		return tipo;
	}

	public String getTexto() {
		return texto;
	}

	public Date getFecha() {
		return fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}
}
