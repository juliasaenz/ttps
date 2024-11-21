package ttps.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ttps.spring.dao.CompraDAO;
import ttps.spring.model.Compra;

@Service
public class CompraService {

    @Autowired
    private CompraDAO compraDAO;

    @Transactional
    public Compra registrarCompra(Compra compra) {
        return compraDAO.persistir(compra);
    }

    @Transactional(readOnly = true)
    public List<Compra> listarCompras() {
        return compraDAO.recuperarTodos("fecha");
    }

    @Transactional(readOnly = true)
    public List<Compra> verComprasDia(Date dia) {
        return compraDAO.verComprasDia(dia);
    }

    @Transactional(readOnly = true)
    public List<Compra> verComprasDeCliente(Long clienteId) {
        return compraDAO.verComprasDeCliente(clienteId);
    }

    @Transactional(readOnly = true)
    public List<Compra> verComprasDeMenu(Long menuId) {
        return compraDAO.verComprasDeMenu(menuId);
    }

    @Transactional(readOnly = true)
    public boolean clienteComproParaDia(Long clienteId, Date dia) {
        return compraDAO.clienteComproParaDia(clienteId, dia);
    }

    @Transactional
    public void eliminarCompra(Long id) {
        Compra compra = compraDAO.recuperar(id);
        if (compra != null) {
            compraDAO.borrar(compra);
        } else {
            throw new IllegalArgumentException("La compra no existe");
        }
    }
}
