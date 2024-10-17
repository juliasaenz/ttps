package quecomemos.jpa;

import quecomemos.dao.AdministradorDAO;
import quecomemos.model.Administrador;

public class AdministradorDAO_JPA extends GenericDAO_JPA<Administrador> implements AdministradorDAO {

	public AdministradorDAO_JPA() {
		super(Administrador.class);
		// TODO Auto-generated constructor stub
	}

}
