package quecomemos.jpa;

import quecomemos.dao.ComidaDAO;

public class ComidaDAO_JPA<T> extends GenericDAO_JPA<T> implements ComidaDAO<T> {

	public ComidaDAO_JPA(Class<T> clase) {
		super(clase);
		// TODO Auto-generated constructor stub
	}

}
