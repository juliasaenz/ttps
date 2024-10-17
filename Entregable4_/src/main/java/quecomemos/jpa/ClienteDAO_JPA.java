package quecomemos.jpa;

import quecomemos.dao.ClienteDAO;
import quecomemos.model.Cliente;

public class ClienteDAO_JPA extends GenericDAO_JPA<Cliente> implements ClienteDAO {

	public ClienteDAO_JPA() {
		super(Cliente.class);
	}

}
