package quecomemos.jpa;

import java.util.List;

import quecomemos.dao.MenuDAO;
import quecomemos.model.Menu;

public class MenuDAO_JPA extends GenericDAO_JPA<Menu> implements MenuDAO {

	public MenuDAO_JPA() {
		super(Menu.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Menu> getMenusVegetarianos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMenuInCarta() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void borrar(Menu m) {
		// Antes de borrar un menú hay que chequear que no esté en una carta POSTERIOR al día actual
	}

}
