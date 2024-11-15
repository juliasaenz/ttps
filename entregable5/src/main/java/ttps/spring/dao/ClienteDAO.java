
package ttps.spring.dao;

import ttps.spring.model.Cliente;

public interface ClienteDAO extends UsuarioDAO<Cliente> {

    boolean isVegetariano(Long clienteId);


}