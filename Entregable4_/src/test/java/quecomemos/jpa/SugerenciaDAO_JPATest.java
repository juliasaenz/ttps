package quecomemos.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import quecomemos.dao.SugerenciaDAO;
import quecomemos.model.Cliente;
import quecomemos.model.Sugerencia;
import quecomemos.util.EMF;
import quecomemos.util.TipoSugerencia;

@TestInstance(Lifecycle.PER_CLASS)
public class SugerenciaDAO_JPATest {

    private SugerenciaDAO sugerenciaDao;
    private EntityManager em;

    @BeforeEach
    public void setUp() throws Exception {
        sugerenciaDao = new SugerenciaDAO_JPA();
        em = EMF.getEMF().createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Cliente cliente = new Cliente("12345678", "password", "John", "Doe", "john.doe@example.com");
        em.persist(cliente);

        Sugerencia sugerencia1 = new Sugerencia(TipoSugerencia.SERVICIO, "Que buen servicio", cliente);
        Sugerencia sugerencia2 = new Sugerencia(TipoSugerencia.COMIDA, "Hay pocas opciones vegetarianas", cliente);
        em.persist(sugerencia1);
        em.persist(sugerencia2);

        tx.commit();
    }

    @AfterEach
    public void tearDown() throws Exception {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Sugerencia").executeUpdate();
        em.createQuery("DELETE FROM Cliente").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testPersistir() {
        Cliente cliente = new Cliente("87654321", "password2", "Jane", "Doe", "jane.doe@example.com");
        em.getTransaction().begin();
        em.persist(cliente); 
        em.getTransaction().commit();

        Sugerencia nuevaSugerencia = new Sugerencia(TipoSugerencia.COMIDA, "La comida estaba fría", cliente);
        sugerenciaDao.persistir(nuevaSugerencia);

        Sugerencia s = sugerenciaDao.recuperar(nuevaSugerencia.getId());
        assertNotNull(s);
        assertEquals(nuevaSugerencia.getId(), s.getId());
    }

    @Test
    public void testBorrar() {
        List<Sugerencia> sugerencias = sugerenciaDao.recuperarTodos(null);
        assertEquals(2,sugerencias.size());
        
        sugerenciaDao.borrar(sugerencias.get(0).getId());
        
        List<Sugerencia> nueLista = sugerenciaDao.recuperarTodos(null);
        assertEquals(1,nueLista.size());
    }

    @Test
    public void testRecuperarTodos() {
        List<Sugerencia> sugerencias = sugerenciaDao.recuperarTodos("fecha");
        assertEquals(2, sugerencias.size());
    }

    @Test
    public void testSugerenciasPorTipo() {
        List<Sugerencia> sugerencias = sugerenciaDao.sugerenciasPorTipo(TipoSugerencia.COMIDA);
        assertNotNull(sugerencias);
        assertEquals(1, sugerencias.size());
        assertEquals("Hay pocas opciones vegetarianas", sugerencias.get(0).getTexto());
    }

}
