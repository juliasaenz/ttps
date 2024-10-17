package quecomemos.jpa;

import quecomemos.dao.PersonalDAO;
import quecomemos.model.Personal;

public class PersonalDAO_JPA extends GenericDAO_JPA<Personal> implements PersonalDAO {

	public PersonalDAO_JPA() {
		super(Personal.class);
		// TODO Auto-generated constructor stub
	}

}
