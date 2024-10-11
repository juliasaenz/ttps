package quecomemos.jpa;

import quecomemos.dao.CartaDAO;

public class CartaDAO_JPA<T> extends GenericDAO_JPA<T> implements CartaDAO<T> {

	public CartaDAO_JPA(Class<T> clase) {
		super(clase);
		// TODO Auto-generated constructor stub
	}

}
