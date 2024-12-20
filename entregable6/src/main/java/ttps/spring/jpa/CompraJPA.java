package ttps.spring.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ttps.spring.dao.CompraDAO;
import ttps.spring.model.Compra;

@Repository
public class CompraJPA extends GenericJPA<Compra> implements CompraDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public CompraJPA() {
        super(Compra.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Compra> verComprasDia(Date dia) {
        TypedQuery<Compra> query = entityManager.createQuery(
                "SELECT c FROM Compra c WHERE c.fecha = :fecha", Compra.class
        );
        query.setParameter("fecha", dia);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Compra> verComprasDeCliente(Long clienteId) {
        TypedQuery<Compra> query = entityManager.createQuery(
                "SELECT c FROM Compra c WHERE c.cliente.id = :clienteId", Compra.class
        );
        query.setParameter("clienteId", clienteId);
        return query.getResultList(); 
    }

    @Override
    @Transactional(readOnly = true)
    public List<Compra> verComprasDeMenu(Long menuId) {
        TypedQuery<Compra> query = entityManager.createQuery(
                "SELECT c FROM Compra c WHERE c.menu.id = :menuId", Compra.class
        );
        query.setParameter("menuId", menuId);
        return query.getResultList(); 
    }

    @Override
    @Transactional(readOnly = true)
    public boolean clienteComproParaDia(Long clienteId, Date dia) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(c) FROM Compra c WHERE c.cliente.id = :clienteId AND c.fecha = :fecha", Long.class
        );
        query.setParameter("clienteId", clienteId);
        query.setParameter("fecha", dia);
        Long count = query.getSingleResult();
        return count > 0; 
    }
}