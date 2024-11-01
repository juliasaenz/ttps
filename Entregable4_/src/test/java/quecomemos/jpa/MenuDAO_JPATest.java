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
import org.junit.jupiter.api.TestInstance.Lifecycle;

import quecomemos.dao.ComidaDAO;
import quecomemos.dao.MenuDAO;
import quecomemos.model.Comida;
import quecomemos.model.Menu;
import quecomemos.util.EMF;
import quecomemos.util.TipoComida;

@TestInstance(Lifecycle.PER_CLASS)
public class MenuDAO_JPATest {

    private MenuDAO menuDao;
    private ComidaDAO comidaDao;
    private EntityManager em;
    private Comida comidaVeggie;
    private Comida comidaNoVeggie;
    private Menu menuVegetariano;
    private Menu menuMixto;

    @BeforeEach
    public void setUp() throws Exception {
        menuDao = new MenuDAO_JPA();
        comidaDao = new ComidaDAO_JPA();
        em = EMF.getEMF().createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Crear comidas con TipoComida (como VEGETARIANO y CARNE por ejemplo)
        comidaVeggie = new Comida("Ensalada", TipoComida.ENTRADA, true);
        comidaNoVeggie = new Comida("Milanesa", TipoComida.POSTRE, false);
        em.persist(comidaVeggie);
        em.persist(comidaNoVeggie);

        // Crear menú vegetariano
        menuVegetariano = new Menu();
        List<Comida> comidasVegetarianas = new ArrayList<>();
        comidasVegetarianas.add(comidaVeggie);
        menuVegetariano.setComidas(comidasVegetarianas);
        menuVegetariano.setPrecio(150.0);
        em.persist(menuVegetariano);

        // Crear menú mixto
        menuMixto = new Menu();
        List<Comida> comidasMixtas = new ArrayList<>();
        comidasMixtas.add(comidaVeggie);
        comidasMixtas.add(comidaNoVeggie);
        menuMixto.setComidas(comidasMixtas);
        menuMixto.setPrecio(200.0);
        em.persist(menuMixto);

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
    public void testPersistirYRecuperar() {
        Menu nuevoMenu = new Menu();
        nuevoMenu.setComidas(List.of(comidaVeggie));
        nuevoMenu.setPrecio(100.0);
        menuDao.persistir(nuevoMenu);

        Menu menuRecuperado = menuDao.recuperar(nuevoMenu.getId());
        assertNotNull(menuRecuperado);
        assertEquals(100.0, menuRecuperado.getPrecio());
    }

    @Test
    public void testActualizar() {
        menuVegetariano.setPrecio(180.0);
        menuDao.actualizar(menuVegetariano);

        Menu menuActualizado = menuDao.recuperar(menuVegetariano.getId());
        assertEquals(180.0, menuActualizado.getPrecio());
    }

    @Test
    public void testBorrar() {
        menuDao.borrar(menuMixto);
        Menu menuEliminado = menuDao.recuperar(menuMixto.getId());
        assertTrue(comidaDao.existe(comidaNoVeggie.getId()));
        assertNull(menuEliminado);
    }

    @Test
    public void testGetMenusVegetarianos() {
        List<Menu> menusVegetarianos = menuDao.getMenusVegetarianos();
        assertNotNull(menusVegetarianos);
        assertFalse(menusVegetarianos.isEmpty());
        assertEquals(1, menusVegetarianos.size());
    }

    @Test
    public void testIsMenuInCarta() {
        assertTrue(menuDao.isMenuInCarta());
        
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Menu").executeUpdate();
        em.getTransaction().commit();
        
        assertFalse(menuDao.isMenuInCarta());
    }
}

