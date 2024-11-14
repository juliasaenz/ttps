package ttps.spring.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ttps.spring.dao.ComidaDAO;
import ttps.spring.model.Comida;
import ttps.spring.model.TipoComida;

@Repository
public class ComidaJPA extends GenericJPA<Comida> implements ComidaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public ComidaJPA() {
        super(Comida.class);
    }

    @Override
    public List<Comida> findByTipo(TipoComida tipo) {
        TypedQuery<Comida> query = entityManager.createQuery(
            "SELECT c FROM Comida c WHERE c.tipo = :tipo", Comida.class
        );
        query.setParameter("tipo", tipo);
        return query.getResultList();
    }

    @Override
    public List<Comida> findVegetarian(boolean vegetariano) {
        TypedQuery<Comida> query = entityManager.createQuery(
            "SELECT c FROM Comida c WHERE c.vegetariano = :vegetariano", Comida.class
        );
        query.setParameter("vegetariano", vegetariano);
        return query.getResultList();
    }

    @Override
    public boolean isComidaInMenu(Long comidaId) {
        /*TypedQuery<Menu> query = entityManager.createQuery(
            "SELECT m FROM Menu m JOIN m.comidas c WHERE c.id = :comidaId", Menu.class
        );
        query.setParameter("comidaId", comidaId);
        return !query.getResultList().isEmpty();*/
    	return false;
    }

    @Override
    public boolean existsByNombre(String nombre) {
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(c) FROM Comida c WHERE c.nombre = :nombre", Long.class
        );
        query.setParameter("nombre", nombre);
        return query.getSingleResult() > 0;
    }

    @Override
    public void borrar(Comida comida) {
        if (isComidaInMenu(comida.getId())) {
            throw new IllegalStateException("No se puede borrar la comida, la está usando un menú");
        }
        super.borrar(comida);
    }

    @Override
    public Comida persistir(Comida comida) {
        if (existsByNombre(comida.getNombre())) {
            throw new IllegalArgumentException("Ya existe una comida con este nombre");
        }
        return super.persistir(comida);
    }
}
