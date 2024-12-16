package ttps.spring.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ttps.spring.dao.GenericDAO;

@Transactional
public abstract class GenericJPA<T> implements GenericDAO<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected Class<T> clasePersistente;

    public GenericJPA(Class<T> clase) {
        this.clasePersistente = clase;
    }

    @Override
    public T persistir(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T actualizar(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void borrar(T entity) {
        entityManager.remove(entityManager.merge(entity));
    }

    @Override
    public T borrar(Long id) {
        T entity = entityManager.find(clasePersistente, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
        return entity;
    }

    @Override
    public boolean existe(Long id) {
        return entityManager.find(clasePersistente, id) != null;
    }

    @Override
    public T recuperar(Serializable id) {
        return entityManager.find(clasePersistente, id);
    }

    @Override
    public List<T> recuperarTodos(String column) {
        String orderByColumn = (column == null || column.isEmpty()) ? "id" : column;
        return entityManager.createQuery(
                "SELECT e FROM " + clasePersistente.getSimpleName() + " e ORDER BY e." + orderByColumn, clasePersistente)
                .getResultList();
    }
}
