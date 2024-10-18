package quecomemos.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "Cartas")
public class Carta {
	
	@Column
	private Date semana;

	@Column
	private Menu menu = new Menu();

	@Column
	private Menu menuVeggie = new Menu();
	
	public Carta(Date dia) {
		super();
		this.dia = dia;
	}
	
	public Date getDia() {
		return dia;
	}
	public void setDia(Date dia) {
		this.dia = dia;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Menu getMenuVeggie() {
		return menuVeggie;
	}
	public void setMenusVeggie(Menu menuVeggie) {
		this.menuVeggie = menuVeggie;
	}
}
