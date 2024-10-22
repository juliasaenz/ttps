package quecomemos.jpa;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import quecomemos.dao.ClienteDAO;
import quecomemos.model.Cliente;
import quecomemos.util.EMF;

public class ClienteDAO_JPA extends UsuarioDAO_JPA<Cliente> implements ClienteDAO {

	public ClienteDAO_JPA() {
		super(Cliente.class);
	}

	@Override
	public boolean isVegetariano(Long clienteId) {
	    EntityManager em = EMF.getEMF().createEntityManager();
	    try {
	        Boolean vegetariano = em.createQuery("SELECT c.vegetariano FROM Cliente c WHERE c.id = :clienteId", Boolean.class)
	                               .setParameter("clienteId", clienteId)
	                               .getSingleResult();
	        return vegetariano != null ? vegetariano : false;
	    } catch (NoResultException e) {
	        return false;
	    } finally {
	        em.close();
	    }
	}

}
