package quecomemos.dao;
import quecomemos.model.Cliente;

public interface ClienteDAO extends GenericDAO<Cliente> {
	
	boolean isVegetariano(Long clienteId);


}
