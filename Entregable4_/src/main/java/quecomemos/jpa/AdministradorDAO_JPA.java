package quecomemos.jpa;

import quecomemos.dao.AdministradorDAO;

public class AdministradorDAO_JPA<T> extends GenericDAO_JPA<T> implements AdministradorDAO<T> {

	public AdministradorDAO_JPA(Class<T> clase) {
		super(clase);
		// TODO Auto-generated constructor stub
	}

}
