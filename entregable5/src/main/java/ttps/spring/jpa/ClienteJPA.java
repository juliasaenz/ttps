package ttps.spring.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import ttps.spring.dao.ClienteDAO;
import ttps.spring.model.Cliente;

@Repository
public class ClienteJPA extends UsuarioJPA<Cliente> implements ClienteDAO {

    @PersistenceContext
    private EntityManager entityManager; // Spring injects the EntityManager

    public ClienteJPA() {
        super(Cliente.class);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isVegetariano(Long clienteId) {
        try {
            Boolean vegetariano = entityManager.createQuery(
                            "SELECT c.vegetariano FROM Cliente c WHERE c.id = :clienteId", Boolean.class)
                    .setParameter("clienteId", clienteId)
                    .getSingleResult();
            return vegetariano != null ? vegetariano : false;
        } catch (NoResultException e) {
            return false; // Return false if no result found for the query.
        }
    }
}
