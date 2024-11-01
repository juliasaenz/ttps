package quecomemos.jpa;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import quecomemos.dao.CartaDAO;
import quecomemos.model.Carta;
import quecomemos.model.Menu;
import quecomemos.util.EMF;

public class CartaDAO_JPA extends GenericDAO_JPA<Carta> implements CartaDAO {

    public CartaDAO_JPA() {
		super(Carta.class);
	}

	@Override
    public Carta actualizar(Carta entity) {
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        Carta updatedEntity = em.merge(entity);
        em.getTransaction().commit();
        em.close();
        return updatedEntity;
    }

    @Override
    public void borrar(Carta entity) {
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        em.remove(entity);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Carta borrar(Long id) {
        EntityManager em = EMF.getEMF().createEntityManager();
        Carta entity = em.find(Carta.class, id);
        if (entity != null) {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        }
        em.close();
        return entity;
    }

    @Override
    public boolean existe(Long id) {
        EntityManager em = EMF.getEMF().createEntityManager();
        Carta entity = em.find(Carta.class, id);
        em.close();
        return entity != null;
    }

   /* @Override
    public Carta persistir(Carta entity) {
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
        return entity;
    }*/

    @Override
    public Carta recuperar(Serializable id) {
        EntityManager em = EMF.getEMF().createEntityManager();
        Carta entity = em.find(Carta.class, id);
        em.close();
        return entity;
    }

    @Override
    public List<Carta> recuperarTodos() {
        EntityManager em = EMF.getEMF().createEntityManager();
        TypedQuery<Carta> query = em.createQuery("SELECT c FROM Carta c ORDER BY c.dia", Carta.class);
        List<Carta> result = query.getResultList();
        em.close();
        return result;
    }

    @Override
    public Carta getCartaDia(LocalDate d) {
        EntityManager em = EMF.getEMF().createEntityManager();
        TypedQuery<Carta> query = em.createQuery(
            "SELECT c FROM Carta c WHERE c.dia = :dia", Carta.class
        );
        query.setParameter("dia", d);
        Carta result = query.getSingleResult();
        em.close();
        return result;
    }

    @Override
    public List<Carta> getCartaSemana(Date d) {
        EntityManager em = EMF.getEMF().createEntityManager();
        TypedQuery<Carta> query = em.createQuery(
            "SELECT c FROM Carta c WHERE c.dia BETWEEN :startDate AND :endDate", Carta.class
        );
        // Asumiendo que 'd' es el primer día de la semana
        query.setParameter("startDate", d);
        query.setParameter("endDate", new Date(d.getTime() + (7 * 24 * 60 * 60 * 1000))); // 7 días después
        List<Carta> result = query.getResultList();
        em.close();
        return result;
    }

    @Override
    public List<Menu> getMenusDia(Date dia) {
        EntityManager em = EMF.getEMF().createEntityManager();
        TypedQuery<Menu> query = em.createQuery(
            "SELECT c.menu FROM Carta c WHERE c.dia = :dia", Menu.class
        );
        query.setParameter("dia", dia);
        List<Menu> result = query.getResultList();
        em.close();
        return result;
    }

    @Override
    public Menu getMenuVeggieDia(Date dia) {
        EntityManager em = EMF.getEMF().createEntityManager();
        TypedQuery<Menu> query = em.createQuery(
            "SELECT c.menuVeggie FROM Carta c WHERE c.dia = :dia", Menu.class
        );
        query.setParameter("dia", dia);
        Menu result = query.getSingleResult();
        em.close();
        return result;
    }

	
}