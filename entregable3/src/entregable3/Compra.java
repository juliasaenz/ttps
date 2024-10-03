package entregable3;

import java.sql.Date;

public class Compra {
	Cliente cliente;
	Date fecha;
	Menu menu;
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
	
	

}
