package quecomemos.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import quecomemos.model.Carta;
import quecomemos.model.Menu;

public interface CartaDAO extends GenericDAO<Carta> {
    public List<Carta> getCartaSemana(Date d);

    public List<Menu> getMenusDia(Date dia);

    public Menu getMenuVeggieDia(Date dia);

	public List<Carta> recuperarTodos();

	public Carta getCartaDia(LocalDate d);
}
