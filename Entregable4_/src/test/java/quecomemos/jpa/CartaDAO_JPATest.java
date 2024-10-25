package quecomemos.jpa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import quecomemos.dao.MenuDAO;
import quecomemos.model.Carta;
import quecomemos.model.Comida;
import quecomemos.model.Menu;
import quecomemos.util.EMF;
import quecomemos.util.TipoComida;

public class CartaDAO_JPATest {

    private CartaDAO_JPA cartaDAO;
    private MenuDAO_JPA menuDAO;
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        cartaDAO = new CartaDAO_JPA();
        menuDAO = new MenuDAO_JPA();
        em = EMF.getEMF().createEntityManager();
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
    	Comida nuevaComida = new Comida("Pasta", TipoComida.PLATO_PRINCIPAL, true);
        
        MenuDAO menuDao = new MenuDAO_JPA();
        Menu menu = new Menu();
        
        List<Comida> lista = new ArrayList<>();
        lista.add(nuevaComida);
        menu.setComidas(lista);
        menuDao.actualizar(menu);
        
        Carta carta = new Carta( LocalDate.now());
        carta.setMenu(menu);
        cartaDAO.persistir(carta);

        
        
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
