package entregable3;

import java.sql.Date;
import java.util.ArrayList;

public class Plataforma {
	ArrayList<Usuario> usuarios;
	ArrayList<Comida> comidas;
	ArrayList<Menu> menus;
	ArrayList<Carta> cartas;
	ArrayList<Sugerencia> sugerencias;
	ArrayList<Compra> compras ;
	
	

	public Plataforma() {
		usuarios = new ArrayList<Usuario>();
		comidas = new ArrayList<Comida>();
		menus = new ArrayList<Menu>();
		cartas = new ArrayList<Carta>();
		sugerencias = new ArrayList<Sugerencia>();
		compras = new ArrayList<Compra>();
	}

	public Carta verCartaSemana(Date semana) {
		return null;}
	
	public ArrayList<Sugerencia> verSugerencias(){
		return null;}
	
	public void crearUsuario(String dni, String clave, String nombre, String apellido, String email) {
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public ArrayList<Comida> getComidas() {
		return comidas;
	}

	public ArrayList<Menu> getMenus() {
		return menus;
	}

	public ArrayList<Carta> getCartas() {
		return cartas;
	}

	public ArrayList<Sugerencia> getSugerencias() {
		return sugerencias;
	}
	
	
}
