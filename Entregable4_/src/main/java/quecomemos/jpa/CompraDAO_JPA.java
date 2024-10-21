package quecomemos.jpa;

import java.util.Date;
import java.util.List;

import quecomemos.dao.CompraDAO;
import quecomemos.model.Compra;

public class CompraDAO_JPA extends GenericDAO_JPA<Compra> implements CompraDAO {

	public CompraDAO_JPA() {
		super(Compra.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Compra> verComprasDia(Date semana) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compra> verComprasCliente(Long clienteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compra> verComprasMenu(Long menuId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean clienteComproParaDia(Long clienteId, Date dia) {
		// TODO Auto-generated method stub
		return false;
	}


}
