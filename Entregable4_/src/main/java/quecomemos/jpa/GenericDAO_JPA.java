package quecomemos.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import quecomemos.dao.GenericDAO;
import quecomemos.util.EMF;

public class GenericDAO_JPA<T> implements GenericDAO<T> {
	protected Class<T> clasePersistente;

	public GenericDAO_JPA(Class<T> clase) {
		clasePersistente = clase;
	}

	@Override
	public T persistir(T entity) {
		EntityManager em = EMF.getEMF().createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(entity);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e; // escribir en un log o mostrar un mensaje
		} finally {
			em.close();
		}
		return entity;
	}

	public T actualizar(T entity) {
		EntityManager em = EMF.getEMF().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		T entityMerged = em.merge(entity);
		etx.commit();
		em.close();
		return entityMerged;
	}

	@Override
	public void borrar(T entity) {
		EntityManager em = EMF.getEMF().createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.remove(em.merge(entity));
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e; // escribir en un log o mostrar un mensaje
		} finally {
			em.close();
		}
	}

	@Override
	public T borrar(Long id) {
		EntityManager em = EMF.getEMF().createEntityManager();
		T entity = (T) em.find(this.getPersistentClass(), id);
		if (entity != null) {
			em.remove(entity);
		}
		em.close();
		return entity;
	}

	// TODO Preguntar si esto va acá
	private Class getPersistentClass() {
		return clasePersistente;
	}

	@Override
	public boolean existe(Long id) {
	    EntityManager em = EMF.getEMF().createEntityManager();
	    boolean exists = em.find(clasePersistente, id) != null;
	    em.close();
	    return exists;
	}


	@Override
	public T recuperar(Serializable id) {
	    EntityManager em = EMF.getEMF().createEntityManager();
	    T entity = em.find(clasePersistente, id);
	    em.close();
	    return entity;
	}


	@Override
	public List<T> recuperarTodos(String column) {
	    EntityManager em = EMF.getEMF().createEntityManager();
	    List<T> entities = em.createQuery("SELECT e FROM " + clasePersistente.getSimpleName() + " e ORDER BY e." + column, clasePersistente)
	                         .getResultList();
	    em.close();
	    return entities;
	}


}