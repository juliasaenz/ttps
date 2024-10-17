package quecomemos.jpa;

import quecomemos.dao.ResponsableDAO;
import quecomemos.model.Responsable;

public class ResponsableDAO_JPA extends GenericDAO_JPA<Responsable> implements ResponsableDAO {

	public ResponsableDAO_JPA() {
		super(Responsable.class);
		// TODO Auto-generated constructor stub
	}

}
