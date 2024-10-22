package quecomemos.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import quecomemos.dao.SugerenciaDAO;
import quecomemos.model.Sugerencia;
import quecomemos.util.EMF;
import quecomemos.util.TipoSugerencia;

public class SugerenciaDAO_JPA extends GenericDAO_JPA<Sugerencia> implements SugerenciaDAO {

	public SugerenciaDAO_JPA() {
		super(Sugerencia.class);
	}

	@Override
	public List<Sugerencia> sugerenciasPorTipo(TipoSugerencia tipo) {
		EntityManager em = EMF.getEMF().createEntityManager();
        TypedQuery<Sugerencia> query = em.createQuery(
                "SELECT r FROM Sugerencia r WHERE r.tipo = :tipo", this.clasePersistente);
        query.setParameter("tipo", tipo);
        List<Sugerencia> result = query.getResultList();
        em.close();
        return result;
	}

}
