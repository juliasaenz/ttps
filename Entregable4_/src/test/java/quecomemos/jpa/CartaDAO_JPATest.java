package quecomemos.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        //Crear carta
        carta = new Carta();
        carta.setMenu(menuMixto);
        //em.persist(carta);
         
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
    public void prueba() {
    	System.out.println("hola");
    }

    /*@Test
    public void testPersistirCarta() {
        Carta nuevaCarta = new Carta(LocalDate.now());
        nuevaCarta.setMenu(menuMixto);
        cartaDao.persistir(nuevaCarta);

        Carta cartaRecuperada = cartaDao.recuperar(nuevaCarta.getId());
        assertNotNull(cartaRecuperada);
        assertEquals(nuevaCarta.getDia(), cartaRecuperada.getDia());
    }


    /*@Test
    public void testActualizarCarta() {
        // Crear una carta
        Menu menu = new Menu(); // Crear y configurar el menú
        menuDAO.persistir(menu);

        Carta carta = new Carta(Date.valueOf("2024-10-25"));
        carta.setMenu(menu);
        cartaDAO.persistir(carta);

        // Actualizar la carta
        carta.setDia(Date.valueOf("2024-10-26"));
        Carta updatedCarta = cartaDAO.actualizar(carta);

        assertEquals(Date.valueOf("2024-10-26"), updatedCarta.getDia());
    }

    @Test
    public void testBorrarCarta() {
        // Crear y persistir una carta
        Menu menu = new Menu(); // Crear y configurar el menú
        menuDAO.persistir(menu);

        Carta carta = new Carta(Date.valueOf("2024-10-25"));
        carta.setMenu(menu);
        cartaDAO.persistir(carta);

        // Borrar la carta
        cartaDAO.borrar(carta);

        // Verificar que la carta fue borrada
        Carta deletedCarta = cartaDAO.recuperar(carta.getId());
        assertNull(deletedCarta);
    }

    @Test
    public void testRecuperarCarta() {
        // Crear y persistir una carta
        Menu menu = new Menu(); // Crear y configurar el menú
        menuDAO.persistir(menu);

        Carta carta = new Carta(Date.valueOf("2024-10-25"));
        carta.setMenu(menu);
        cartaDAO.persistir(carta);

        // Recuperar la carta
        Carta retrievedCarta = cartaDAO.recuperar(carta.getId());

        assertNotNull(retrievedCarta);
        assertEquals(carta.getId(), retrievedCarta.getId());
    }

    @Test
    public void testRecuperarTodosCartas() {
        // Crear y persistir varias cartas
        Menu menu = new Menu(); // Crear y configurar el menú
        menuDAO.persistir(menu);

        Carta carta1 = new Carta(Date.valueOf("2024-10-25"));
        carta1.setMenu(menu);
        cartaDAO.persistir(carta1);

        Carta carta2 = new Carta(Date.valueOf("2024-10-26"));
        carta2.setMenu(menu);
        cartaDAO.persistir(carta2);

        // Recuperar todas las cartas
        List<Carta> cartas = cartaDAO.recuperarTodos("dia");

        assertEquals(2, cartas.size());
    }

    @Test
    public void testGetCartaDia() {
        // Crear y persistir una carta
        Menu menu = new Menu(); // Crear y configurar el menú
        menuDAO.persistir(menu);

        Carta carta = new Carta(Date.valueOf("2024-10-25"));
        carta.setMenu(menu);
        cartaDAO.persistir(carta);

        // Recuperar la carta por día
        Carta cartaRecuperada = cartaDAO.getCartaDia(Date.valueOf("2024-10-25"));

        assertNotNull(cartaRecuperada);
        assertEquals(carta.getId(), cartaRecuperada.getId());
    }

    @Test
    public void testGetMenusDia() {
        // Crear y persistir una carta
        Menu menu = new Menu(); // Crear y configurar el menú
        menuDAO.persistir(menu);

        Carta carta = new Carta(Date.valueOf("2024-10-25"));
        carta.setMenu(menu);
        cartaDAO.persistir(carta);

        // Obtener menús del día
        List<Menu> menus = cartaDAO.getMenusDia(Date.valueOf("2024-10-25"));

        assertEquals(1, menus.size());
        assertEquals(menu.getId(), menus.get(0).getId());
    }*/

}
