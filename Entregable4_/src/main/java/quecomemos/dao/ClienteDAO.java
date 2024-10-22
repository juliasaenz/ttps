package quecomemos.dao;

import quecomemos.model.Cliente;

public interface ClienteDAO extends UsuarioDAO<Cliente> {

	boolean isVegetariano(Long clienteId);


}
