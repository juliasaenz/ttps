package ttps.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttps.spring.dao.ClienteDAO;
import ttps.spring.model.Cliente;
import ttps.spring.model.Compra;

import java.sql.Date;
import java.util.List;

@Service
public class ClienteService extends UsuarioService<Cliente> {

    @Autowired
    private ClienteDAO clienteDAO;

    @Transactional
    public Cliente registrarCliente(Cliente cliente) {
        if (clienteDAO.existsByEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        if (clienteDAO.existsByDni(cliente.getDni())) {
            throw new IllegalArgumentException("El DNI ya está registrado");
        }
        return clienteDAO.persistir(cliente);
    }

    @Transactional
    public void eliminarCliente(Long id) {
        Cliente cliente = clienteDAO.recuperar(id);
        if (cliente != null) {
            clienteDAO.borrar(cliente);
        } else {
            throw new IllegalArgumentException("El cliente no existe");
        }
    }

    @Transactional(readOnly = true)
    public List<Cliente> listarClientes() {
        return clienteDAO.recuperarTodos("nombre");
    }

    @Transactional(readOnly = true)
    public boolean isClienteVegetariano(Long clienteId) {
        return clienteDAO.isVegetariano(clienteId);
    }

    @Transactional(readOnly = true)
    public Compra getCompraDelDia(Long clienteId, Date fecha) {
        Cliente cliente = clienteDAO.recuperar(clienteId);
        Compra compra =  cliente.getCompraDia(fecha);
        return compra != null ? compra : null;
    }
}
