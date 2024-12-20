package ttps.spring.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ttps.spring.dao.ResponsableDAO;
import ttps.spring.model.Responsable;

@Repository
public class ResponsableJPA extends UsuarioJPA<Responsable> implements ResponsableDAO {

    @PersistenceContext
    private EntityManager entityManager; 

    public ResponsableJPA() {
        super(Responsable.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Responsable> getResponsablesbyTurno(String turno) {
        TypedQuery<Responsable> query = entityManager.createQuery(
                "SELECT r FROM Responsable r WHERE r.turno = :turno", Responsable.class);
        query.setParameter("turno", turno);
        return query.getResultList(); 
    }
}
