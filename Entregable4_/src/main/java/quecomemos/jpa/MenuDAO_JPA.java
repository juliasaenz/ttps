package quecomemos.jpa;

import quecomemos.dao.MenuDAO;

public class MenuDAO_JPA<T> extends GenericDAO_JPA<T> implements MenuDAO<T> {

	public MenuDAO_JPA(Class<T> clase) {
		super(clase);
		// TODO Auto-generated constructor stub
	}

}
