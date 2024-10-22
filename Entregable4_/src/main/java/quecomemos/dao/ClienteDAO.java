package quecomemos.dao;
import quecomemos.model.Cliente;

public interface ClienteDAO extends UsuarioDAO {
	
	boolean isVegetariano(Long clienteId);


}
