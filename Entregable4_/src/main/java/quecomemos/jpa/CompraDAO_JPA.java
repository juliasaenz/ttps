package quecomemos.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import quecomemos.dao.CompraDAO;
import quecomemos.model.Compra;
import quecomemos.model.Menu;
import quecomemos.util.EMF;

public class CompraDAO_JPA extends GenericDAO_JPA<Compra> implements CompraDAO {

    public CompraDAO_JPA() {
		super(Compra.class);
	}

	@Override
    public Compra actualizar(Compra entity) {
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        Compra updatedEntity = em.merge(entity);
        em.getTransaction().commit();
        em.close();
        return updatedEntity;
    }

    @Override
    public void borrar(Compra entity) {
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
    public Compra borrar(Long id) {
        EntityManager em = EMF.getEMF().createEntityManager();
        Compra entity = em.find(Compra.class, id);
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
        Compra entity = em.find(Compra.class, id);
        em.close();
        return entity != null;
    }

    @Override
    public Compra persistir(Compra entity) {
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
        return entity;
    }

   /* @Override
    public Compra recuperar(Long id) {
        EntityManager em = EMF.getEMF().createEntityManager();
        Compra entity = em.find(Compra.class, id);
        em.close();
        return entity;
    }*/

    @Override
    public List<Compra> recuperarTodos(String column) {
        EntityManager em = EMF.getEMF().createEntityManager();
        TypedQuery<Compra> query = em.createQuery("SELECT c FROM Compra c ORDER BY c." + column, Compra.class);
        List<Compra> result = query.getResultList();
        em.close();
        return result;
    }

    @Override
    public List<Compra> verComprasDia(Date dia) {
        EntityManager em = EMF.getEMF().createEntityManager();
        TypedQuery<Compra> query = em.createQuery(
            "SELECT c FROM Compra c WHERE c.fecha = :fecha", Compra.class
        );
        query.setParameter("fecha", dia);
        List<Compra> result = query.getResultList();
        em.close();
        return result;
    }

    @Override
    public List<Compra> verComprasDeCliente(Long clienteId) {
        EntityManager em = EMF.getEMF().createEntityManager();
        TypedQuery<Compra> query = em.createQuery(
            "SELECT c FROM Compra c WHERE c.cliente.id = :clienteId", Compra.class
        );
        query.setParameter("clienteId", clienteId);
        List<Compra> result = query.getResultList();
        em.close();
        return result;
    }

    @Override
    public List<Compra> verComprasDeMenu(Long menuId) {
        EntityManager em = EMF.getEMF().createEntityManager();
        TypedQuery<Compra> query = em.createQuery(
            "SELECT c FROM Compra c WHERE c.menu.id = :menuId", Compra.class
        );
        query.setParameter("menuId", menuId);
        List<Compra> result = query.getResultList();
        em.close();
        return result;
    }

    @Override
    public boolean clienteComproParaDia(Long clienteId, Date dia) {
        EntityManager em = EMF.getEMF().createEntityManager();
        TypedQuery<Long> query = em.createQuery(
            "SELECT COUNT(c) FROM Compra c WHERE c.cliente.id = :clienteId AND c.fecha = :fecha", Long.class
        );
        query.setParameter("clienteId", clienteId);
        query.setParameter("fecha", dia);
        Long count = query.getSingleResult();
        em.close();
        return count > 0;
    }
}