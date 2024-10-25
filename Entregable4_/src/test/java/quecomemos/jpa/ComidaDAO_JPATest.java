package quecomemos.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import quecomemos.dao.MenuDAO;
import quecomemos.model.Comida;
import quecomemos.model.Menu;
import quecomemos.util.EMF;
import quecomemos.util.TipoComida;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ComidaDAO_JPATest {

    private ComidaDAO_JPA comidaDao;
    private EntityManager em;
    Comida comidaVeggie;
    Comida comidaNoVeggie;

    @BeforeEach
    public void setUp() throws Exception {
        comidaDao = new ComidaDAO_JPA();
        em = EMF.getEMF().createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        comidaVeggie = comidaDao.persistir(new Comida("Ensalada", TipoComida.ENTRADA, true));
        comidaNoVeggie = comidaDao.persistir(new Comida("Milanesa", TipoComida.PLATO_PRINCIPAL, false));

        tx.commit();
        }

    @AfterEach
    public void tearDown() throws Exception {
    	em.getTransaction().begin();
    	em.createQuery("DELETE FROM Menu").executeUpdate();
        em.createQuery("DELETE FROM Comida").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testPersistirComida() {
        Comida nuevaComida = new Comida("Pasta", TipoComida.PLATO_PRINCIPAL, true);
        comidaDao.persistir(nuevaComida);

        Comida comidaRecuperada = comidaDao.recuperar(nuevaComida.getId());
        assertNotNull(comidaRecuperada);
        assertEquals("Pasta", comidaRecuperada.getNombre());
        assertTrue(comidaRecuperada.isVegetariano());
    }

    @Test
    public void testFindByTipo() {
        List<Comida> entradas = comidaDao.findByTipo(TipoComida.ENTRADA);
        assertNotNull(entradas);
        assertFalse(entradas.isEmpty());
        assertEquals(1, entradas.size());
    }

    @Test
    public void testFindVegetarian() {
    	List<Comida> veggies = comidaDao.findVegetarian(true);
        assertNotNull(veggies);
        assertFalse(veggies.isEmpty());
        assertEquals(1, veggies.size());
    }

    @Test
    public void testIsComidaInMenu() {
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
        assertFalse(comidaDao.isComidaInMenu(comidaVeggie.getId()));
        
        MenuDAO menuDao = new MenuDAO_JPA();
        
        Menu menu = new Menu();
        
        comidaVeggie = comidaDao.recuperar(comidaVeggie.getId());
        List<Comida> lista = new ArrayList<>();
        lista.add(comidaVeggie);
        menu.setComidas(lista);

        menuDao.persistir(menu); 

        assertTrue(comidaDao.isComidaInMenu(comidaVeggie.getId()));
        tx.commit();
    }

    @Test
    public void testExistsByNombre() {
        assertTrue(comidaDao.existsByNombre(comidaVeggie.getNombre()));
        assertFalse(comidaDao.existsByNombre("ComidaNoExistente"));
    }

    @Test
    public void testBorrar() {
        comidaDao.borrar(comidaVeggie);
        assertNull(comidaDao.recuperar(comidaVeggie.getId()));
    }
}
