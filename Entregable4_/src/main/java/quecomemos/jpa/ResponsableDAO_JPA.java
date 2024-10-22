package quecomemos.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import quecomemos.dao.ResponsableDAO;
import quecomemos.model.Responsable;
import quecomemos.util.EMF;

public class ResponsableDAO_JPA extends UsuarioDAO_JPA<Responsable> implements ResponsableDAO {

    public ResponsableDAO_JPA() {
        super(Responsable.class);
    }

	@Override
	public List<Responsable> getResponsablesbyTurno(String turno) {
		EntityManager em = EMF.getEMF().createEntityManager();
        TypedQuery<Responsable> query = em.createQuery(
            "SELECT r FROM Responsable r WHERE c.turno = :turno", Responsable.class);
        query.setParameter("turno", turno);
        List<Responsable> result = query.getResultList();
        em.close();
        return result;
	}
	
	
   /* @Override
    * public List<Responsable> recuperarTodos(String column) {
       EntityManager em = EMF.getEMF().createEntityManager();
        List<Responsable> responsables = em.createQuery(
            "SELECT r FROM Responsable r ORDER BY r." + column, Responsable.class)
            .getResultList();
        em.close();
        return responsables;
    }*/

}
