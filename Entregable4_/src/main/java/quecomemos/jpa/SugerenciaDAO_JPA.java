package quecomemos.jpa;

import java.util.Date;
import java.util.List;

import quecomemos.dao.SugerenciaDAO;
import quecomemos.model.Sugerencia;

public class SugerenciaDAO_JPA extends GenericDAO_JPA<Sugerencia> implements SugerenciaDAO {

	public SugerenciaDAO_JPA() {
		super(Sugerencia.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Sugerencia> sugerenciasPorTipo(String tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sugerencia> getFecha(Date fecha) {
		// TODO Auto-generated method stub
		return null;
	}

}
