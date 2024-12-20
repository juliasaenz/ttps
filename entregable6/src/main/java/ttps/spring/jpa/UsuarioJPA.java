package ttps.spring.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import ttps.spring.dao.UsuarioDAO;
import ttps.spring.model.Usuario;

@Repository
public abstract class UsuarioJPA<T extends Usuario> extends GenericJPA<T> implements UsuarioDAO<T> {

    @PersistenceContext
    private EntityManager entityManager; 

    public UsuarioJPA(Class<T> clase) {
        super(clase);
    }

    @Override
    @Transactional(readOnly = true)
    public T findByEmail(String email) {
        try {
            return entityManager.createQuery("SELECT u FROM " + this.clasePersistente.getSimpleName() + " u WHERE u.email = :email", this.clasePersistente)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public T findByDni(String dni) {
        try {
            return entityManager.createQuery("SELECT u FROM " + this.clasePersistente.getSimpleName() + " u WHERE u.dni = :dni", this.clasePersistente)
                    .setParameter("dni", dni)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public T autenticar(String dni, String clave) {
        try {
            return entityManager.createQuery("SELECT u FROM " + this.clasePersistente.getSimpleName() + " u WHERE u.dni = :dni AND u.clave = :clave", this.clasePersistente)
                    .setParameter("dni", dni)
                    .setParameter("clave", clave)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByDni(String dni) {
        Long count = entityManager.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.dni = :dni", Long.class)
                .setParameter("dni", dni)
                .getSingleResult();
        return count > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        Long count = entityManager.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();
        return count > 0;
    }

    @Override
    @Transactional
    public T persistir(T usuario) {
        if (existsByDni(usuario.getDni())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese DNI.");
        }
        if (existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese mail.");
        }
        return super.persistir(usuario);
    }
}
