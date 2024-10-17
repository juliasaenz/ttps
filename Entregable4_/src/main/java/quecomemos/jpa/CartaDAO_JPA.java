package quecomemos.jpa;

import quecomemos.dao.CartaDAO;
import quecomemos.model.Carta;

public class CartaDAO_JPA extends GenericDAO_JPA<Carta> implements CartaDAO {

	public CartaDAO_JPA() {
		super(Carta.class);
		// TODO Auto-generated constructor stub
	}

}
