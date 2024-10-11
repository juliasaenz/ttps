package quecomemos.jpa;

import quecomemos.dao.SugerenciaDAO;

public class SugerenciaDAO_JPA<T> extends GenericDAO_JPA<T> implements SugerenciaDAO<T> {

	public SugerenciaDAO_JPA(Class<T> clase) {
		super(clase);
		// TODO Auto-generated constructor stub
	}

}
