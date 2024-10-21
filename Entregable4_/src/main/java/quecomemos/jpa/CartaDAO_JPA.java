package quecomemos.jpa;
import quecomemos.model.Menu;

import java.util.Date;

import quecomemos.dao.CartaDAO;
import quecomemos.model.Carta;

public class CartaDAO_JPA extends GenericDAO_JPA<Carta> implements CartaDAO {

	public CartaDAO_JPA() {
		super(Carta.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void agregarMenu(Menu m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarMenu(Menu m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Carta getCartaDia(Date d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public java.util.List<Carta> getCartaSemana(Date d) {
		// TODO Auto-generated method stub
		return null;
	}

}
