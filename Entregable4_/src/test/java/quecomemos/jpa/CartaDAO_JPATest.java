package quecomemos.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import quecomemos.dao.CartaDAO;
import quecomemos.dao.MenuDAO;
import quecomemos.model.Carta;
import quecomemos.model.Comida;
import quecomemos.model.Menu;
import quecomemos.util.EMF;
import quecomemos.util.TipoComida;

@TestInstance(Lifecycle.PER_CLASS)
public class CartaDAO_JPATest {

	private CartaDAO cartaDao;
    private MenuDAO menuDao;
    private EntityManager em;
    private Carta carta;
    private Menu menuVegetariano;
    private Menu menuMixto;
    private Comida comidaVeggie;
    private Comida comidaNoVeggie;
    
    @BeforeEach
    public void setUp() throws Exception {
        cartaDao = new CartaDAO_JPA();
        menuDao = new MenuDAO_JPA();
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
    public void tearDown() {
    	em.getTransaction().begin();
    	em.createQuery("DELETE FROM Carta").executeUpdate();
    	em.createQuery("DELETE FROM Menu").executeUpdate();
    	em.createQuery("DELETE FROM Comida").executeUpdate();
    	
        em.getTransaction().commit();
        em.close();
    }
    

    @Test
    public void testPersistirCarta() {
        Carta nuevaCarta = new Carta(LocalDate.now());
        nuevaCarta.setMenu(menuMixto);
        nuevaCarta.setMenusVeggie(menuVegetariano);
        cartaDao.persistir(nuevaCarta);

        Carta cartaRecuperada = cartaDao.recuperar(nuevaCarta.getId());
        assertNotNull(cartaRecuperada);
        assertEquals(nuevaCarta.getDia(), cartaRecuperada.getDia());
    }


    @Test
    public void testActualizarCarta() {
        Carta cartaN = new Carta(LocalDate.of(2024, 10, 3));
        cartaN.setMenu(menuMixto);
        cartaN.setMenusVeggie(menuVegetariano);
        cartaDao.persistir(cartaN);

        cartaN.setDia(LocalDate.of(2024, 11, 4));
        Carta updatedCarta = cartaDao.actualizar(cartaN);

        assertEquals(LocalDate.of(2024, 11, 4), updatedCarta.getDia());
    }

    @Test
    public void testBorrarCarta() {
        Carta carta = new Carta(LocalDate.of(2024, 10, 3));
        carta.setMenu(menuMixto);
        carta.setMenusVeggie(menuVegetariano);
        cartaDao.persistir(carta);
        Carta normalCarta = cartaDao.recuperar(carta.getId());
        assertNotNull(normalCarta);

        cartaDao.borrar(carta);

        Carta deletedCarta = cartaDao.recuperar(carta.getId());
        assertNull(deletedCarta);
    }


    @Test
    public void testRecuperarTodosCartas() {

        Carta carta1 = new Carta(LocalDate.of(2024, 10, 4));
        carta1.setMenu(menuMixto);
        carta1.setMenusVeggie(menuVegetariano);
        cartaDao.persistir(carta1);

        Carta carta2 = new Carta(LocalDate.of(2024, 11, 5));
        carta2.setMenu(menuMixto);
        carta2.setMenusVeggie(menuVegetariano);
        cartaDao.persistir(carta2);

        List<Carta> cartas = cartaDao.recuperarTodos();

        assertEquals(2, cartas.size());
    }

    @Test
    public void testGetCartaDia() {

        Carta carta = new Carta(LocalDate.now());
        carta.setMenu(menuMixto);
        carta.setMenusVeggie(menuVegetariano);
        cartaDao.persistir(carta);

        Carta cartaRecuperada = cartaDao.getCartaDia(LocalDate.now());

        assertNotNull(cartaRecuperada);
        assertEquals(carta.getId(), cartaRecuperada.getId());
    }


}
