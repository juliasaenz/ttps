package quecomemos.jpa;

import quecomemos.dao.UsuarioDAO;

public class UsuarioDAO_JPA<T> extends GenericDAO_JPA<T> implements UsuarioDAO<T> {

	public UsuarioDAO_JPA(Class<T> clase) {
		super(clase);
		// TODO Auto-generated constructor stub
	}

}
