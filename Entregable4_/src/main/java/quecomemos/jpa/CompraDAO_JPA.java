package quecomemos.jpa;

import quecomemos.dao.CompraDAO;

public class CompraDAO_JPA<T> extends GenericDAO_JPA<T> implements CompraDAO<T> {

	public CompraDAO_JPA(Class<T> clase) {
		super(clase);
		// TODO Auto-generated constructor stub
	}

}
