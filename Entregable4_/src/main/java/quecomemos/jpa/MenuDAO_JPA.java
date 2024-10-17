package quecomemos.jpa;

import quecomemos.dao.MenuDAO;
import quecomemos.model.Menu;

public class MenuDAO_JPA extends GenericDAO_JPA<Menu> implements MenuDAO {

	public MenuDAO_JPA() {
		super(Menu.class);
		// TODO Auto-generated constructor stub
	}

}
