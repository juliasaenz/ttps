package quecomemos.jpa;

import quecomemos.dao.SugerenciaDAO;
import quecomemos.model.Sugerencia;

public class SugerenciaDAO_JPA extends GenericDAO_JPA<Sugerencia> implements SugerenciaDAO {

	public SugerenciaDAO_JPA() {
		super(Sugerencia.class);
		// TODO Auto-generated constructor stub
	}

}
