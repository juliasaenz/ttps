package quecomemos.model;

import java.sql.Date;

public class Sugerencia {
	String tipo;
	String texto;
	Date fecha;
	Cliente cliente;
	
	public Sugerencia(String tipo, String texto, Cliente cliente) {
		super();
		this.tipo = tipo;
		this.texto = texto;
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Sugerencia [tipo=" + tipo + ", texto=" + texto + ", fecha=" + fecha + ", cliente=" + cliente + "]";
	}

	public String getTipo() {
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
