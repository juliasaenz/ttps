package quecomemos.dao;

import quecomemos.model.Carta;

public interface CartaDAO extends GenericDAO<Carta> {
    public void agregarMenu(Menu m);
	
	public void eliminarMenu(Menu m);
	
	public Carta getCartaDia (Date d);

    public List<Carta> getCartaSemana(Date);
}
