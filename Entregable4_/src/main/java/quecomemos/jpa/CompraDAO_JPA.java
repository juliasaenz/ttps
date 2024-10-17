package quecomemos.jpa;

import quecomemos.dao.CompraDAO;
import quecomemos.model.Compra;

public class CompraDAO_JPA extends GenericDAO_JPA<Compra> implements CompraDAO {

	public CompraDAO_JPA() {
		super(Compra.class);
		// TODO Auto-generated constructor stub
	}

}
