package quecomemos.model;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cartas")
public class Carta {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column
	private Date dia;

	@ManyToOne
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
	private Menu menu = new Menu();

	@ManyToOne
    @JoinColumn(name = "menu_veggie_id", referencedColumnName = "id")
	private Menu menuVeggie = new Menu();

	public Carta(Date dia) {
		super();
		this.dia = dia;
	}

	public Long getId() {
		return id;
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
