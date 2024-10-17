package quecomemos.jpa;

import quecomemos.dao.ComidaDAO;
import quecomemos.model.Comida;

public class ComidaDAO_JPA extends GenericDAO_JPA<Comida> implements ComidaDAO {

	public ComidaDAO_JPA() {
		super(Comida.class);
		// TODO Auto-generated constructor stub
	}

}
