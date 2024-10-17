package quecomemos.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import quecomemos.dao.ComidaDAO;
import quecomemos.model.Comida;
import quecomemos.model.Menu;
import quecomemos.model.TipoComida;

public class ComidaDAO_JPA extends GenericDAO_JPA<Comida> implements ComidaDAO {

    public ComidaDAO_JPA() {
        super(Comida.class);
    }

    @Override
    public List<Comida> findByTipo(TipoComida tipo) {
        EntityManager em = EMF.getEMF().createEntityManager();
        TypedQuery<Comida> query = em.createQuery(
            "SELECT c FROM Comida c WHERE c.tipo = :tipo", Comida.class
        );
        query.setParameter("tipo", tipo);
        List<Comida> result = query.getResultList();
        em.close();
        return result;
    }

    @Override
    public List<Comida> findVegetarian(boolean vegetariano) {
        EntityManager em = EMF.getEMF().createEntityManager();
        TypedQuery<Comida> query = em.createQuery(
            "SELECT c FROM Comida c WHERE c.vegetariano = :vegetariano", Comida.class
        );
        query.setParameter("vegetariano", vegetariano);
        List<Comida> result = query.getResultList();
        em.close();
        return result;
    }

    public boolean isComidaInMenu(Long comidaId) {
        EntityManager em = EMF.getEMF().createEntityManager();
        TypedQuery<Menu> query = em.createQuery(
            "SELECT m FROM Menu m JOIN m.comidas c WHERE c.id = :comidaId", Menu.class
        );
        query.setParameter("comidaId", comidaId);
        boolean inUse = !query.getResultList().isEmpty();
        em.close();
        return inUse;
    }

    public boolean existsByNombre(String nombre) {
        EntityManager em = EMF.getEMF().createEntityManager();
        TypedQuery<Long> query = em.createQuery(
            "SELECT COUNT(c) FROM Comida c WHERE c.nombre = :nombre", Long.class
        );
        query.setParameter("nombre", nombre);
        long count = query.getSingleResult();
        em.close();
        return count > 0;
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
