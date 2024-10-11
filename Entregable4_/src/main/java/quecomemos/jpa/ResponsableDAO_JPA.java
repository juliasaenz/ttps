package quecomemos.jpa;

import quecomemos.dao.ResponsableDAO;

public class ResponsableDAO_JPA<T> extends GenericDAO_JPA<T> implements ResponsableDAO<T> {

	public ResponsableDAO_JPA(Class<T> clase) {
		super(clase);
		// TODO Auto-generated constructor stub
	}

}
