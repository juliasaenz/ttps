package quecomemos.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import quecomemos.dao.UsuarioDAO;
import quecomemos.model.Responsable;
import quecomemos.model.Usuario;
import quecomemos.util.EMF;

public abstract class UsuarioDAO_JPA<T extends Usuario> extends GenericDAO_JPA<T> implements UsuarioDAO<T> {

    public UsuarioDAO_JPA(Class<T> clase) {
        super(clase);
    }

	@Override
    public T findByEmail(String email) {
        EntityManager em = EMF.getEMF().createEntityManager();
        try {
            return em.createQuery("SELECT u FROM " + this.clasePersistente.getSimpleName() +" u WHERE u.email = :email", this.clasePersistente)
                     .setParameter("email", email)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public T findByDni(String dni) {
        EntityManager em = EMF.getEMF().createEntityManager();
        try {
            return em.createQuery("SELECT u FROM " + this.clasePersistente.getSimpleName() + " u WHERE u.dni = :dni", this.clasePersistente)
                     .setParameter("dni", dni)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public T autenticar(String dni, String clave) {
        EntityManager em = EMF.getEMF().createEntityManager();
        System.out.println("The clase persistente es: "+ this.clasePersistente);
        try {
            return em.createQuery("SELECT u FROM " + this.clasePersistente.getSimpleName() + " u WHERE u.dni = :dni AND u.clave = :clave", this.clasePersistente)
                     .setParameter("dni", dni)
                     .setParameter("clave", clave)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
	public boolean existsByDni(String dni) {
        EntityManager em = EMF.getEMF().createEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.dni = :dni", Long.class)
                           .setParameter("dni", dni)
                           .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    @Override
	public boolean existsByEmail(String email) {
        EntityManager em = EMF.getEMF().createEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.email = :email", Long.class)
                           .setParameter("email", email)
                           .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    @Override
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
