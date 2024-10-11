package quecomemos.jpa;

import quecomemos.dao.ClienteDAO;

public class ClienteDAO_JPA<T> extends GenericDAO_JPA<T> implements ClienteDAO<T> {

	public ClienteDAO_JPA(Class<T> clase) {
		super(clase);
		// TODO Auto-generated constructor stub
	}

}
