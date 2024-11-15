package ttps.spring.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ttps.spring.dao.AdministradorDAO;
import ttps.spring.model.Administrador;

@Repository
public class AdministradorJPA extends UsuarioJPA<Administrador> implements AdministradorDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public AdministradorJPA() {
        super(Administrador.class);
    }

    public Administrador findByEmail(String email) {
        TypedQuery<Administrador> query = entityManager.createQuery(
                "SELECT a FROM Administrador a WHERE a.email = :email", Administrador.class
        );
        query.setParameter("email", email);
        return query.getResultList().stream().findFirst().orElse(null);
    }
}
