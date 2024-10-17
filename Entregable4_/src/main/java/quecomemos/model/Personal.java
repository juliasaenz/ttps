package quecomemos.model;

import java.sql.Date;
import java.util.ArrayList;
// Para mi esta hay que borrarla porque nos agrega otra herencia a manejar en tablas al pedo (o unirlos en una tabla)
public class Personal extends Usuario {

	public Personal(String dni, String clave, String nombre, String apellido, String email) {
		super(dni, clave, nombre, apellido, email);
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Compra> verComprasSemana(ArrayList<Compra> c, Date semana){
		return null;
	}
	
}
