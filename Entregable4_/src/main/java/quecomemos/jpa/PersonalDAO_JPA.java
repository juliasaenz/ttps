package quecomemos.jpa;

import quecomemos.dao.PersonalDAO;

public class PersonalDAO_JPA<T> extends GenericDAO_JPA<T> implements PersonalDAO<T> {

	public PersonalDAO_JPA(Class<T> clase) {
		super(clase);
		// TODO Auto-generated constructor stub
	}

}
