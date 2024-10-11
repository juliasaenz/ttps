package quecomemos.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Carta {
	
	Date semana;
	Map<String, Menu> menus = new HashMap<>();
	
	public Carta(Date semana) {
		super();
		this.semana = semana;
	}
	
	public Date getSemana() {
		return semana;
	}
	public void setSemana(Date semana) {
		this.semana = semana;
	}
	public Map<String, Menu> getMenus() {
		return menus;
	}
	public void setMenus(Map<String, Menu> menus) {
		this.menus = menus;
	}
	
	// TODO Consultar esto (y similares)
	public void agregarMenu(Menu m) {
		
	}
	
	public void eliminarMenu(Menu m) {
		
	}
	
	public Menu getMenuDia (String n) {
		return null;
	}
}
