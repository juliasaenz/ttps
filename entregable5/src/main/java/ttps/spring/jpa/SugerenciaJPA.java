package ttps.spring.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.PersistenceContext;


import ttps.spring.dao.SugerenciaDAO;
import ttps.spring.model.Sugerencia;
import ttps.spring.model.TipoSugerencia;

@Repository
public class SugerenciaJPA extends GenericJPA<Sugerencia> implements SugerenciaDAO {

    @PersistenceContext
    private EntityManager entityManager; // Spring automatically injects the EntityManager

    public SugerenciaJPA() {
        super(Sugerencia.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sugerencia> sugerenciasPorTipo(TipoSugerencia tipo) {
        TypedQuery<Sugerencia> query = entityManager.createQuery(
                "SELECT r FROM Sugerencia r WHERE r.tipo = :tipo", this.clasePersistente);
        query.setParameter("tipo", tipo);
        return query.getResultList(); // Return the list of Sugerencias by type
    }
}
