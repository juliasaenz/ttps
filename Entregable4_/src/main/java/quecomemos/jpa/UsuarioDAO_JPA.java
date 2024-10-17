package quecomemos.jpa;

import quecomemos.dao.UsuarioDAO;
import quecomemos.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UsuarioDAO_JPA extends GenericDAO_JPA<Usuario> implements UsuarioDAO {

    public UsuarioDAO_JPA() {
        super(Usuario.class);
    }

    @Override
    public Usuario findByEmail(String email) {
        EntityManager em = EMF.getEMF().createEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                     .setParameter("email", email)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Usuario findByDni(String dni) {
        EntityManager em = EMF.getEMF().createEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.dni = :dni", Usuario.class)
                     .setParameter("dni", dni)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Usuario autenticar(String dni, String clave) {
        EntityManager em = EMF.getEMF().createEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.dni = :dni AND u.clave = :clave", Usuario.class)
                     .setParameter("dni", dni)
                     .setParameter("clave", clave)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
