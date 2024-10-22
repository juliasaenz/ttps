package quecomemos.jpa;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import javax.persistence.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import quecomemos.dao.UsuarioDAO;
import quecomemos.model.Cliente;
import quecomemos.model.Usuario;
import quecomemos.util.EMF;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ClienteDAO_JPATest {

    private UsuarioDAO usuarioDAO;
    private EntityManager em;

    @BeforeEach
    public void setUp() throws Exception {
        usuarioDAO = new ClienteDAO_JPA();
        em = EMF.getEMF().createEntityManager();

        // Populate database with some users (Clientes)
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        Cliente cliente1 = new Cliente("12345678", "password1", "John", "Doe", "john.doe@example.com");
        Cliente cliente2 = new Cliente("87654321", "password2", "Jane", "Doe", "jane.doe@example.com");
        em.persist(cliente1);
        em.persist(cliente2);
        
        tx.commit();
    }

    @AfterEach
    public void tearDown() throws Exception {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Usuario").executeUpdate();  // Clean the database
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testFindByEmail() {
        Cliente user = (Cliente) usuarioDAO.findByEmail("john.doe@example.com");
        assertNotNull(user);
        assertEquals("John", user.getNombre());
    }

    @Test
    public void testFindByDni() {
        Cliente user = (Cliente) usuarioDAO.findByDni("87654321");
        assertNotNull(user);
        assertEquals("Jane", user.getNombre());
    }

    @Test
    public void testAutenticar() {
        Cliente user = (Cliente) usuarioDAO.autenticar("12345678", "password1");
        assertNotNull(user);
        assertEquals("John", user.getNombre());
    }

    @Test
    public void testPersistir() {
        Cliente newUser = new Cliente("11223344", "password3", "Mike", "Smith", "mike.smith@example.com");
        usuarioDAO.persistir(newUser);
        
        Cliente foundUser = (Cliente) usuarioDAO.findByEmail("mike.smith@example.com");
        assertNotNull(foundUser);
        assertEquals("Mike", foundUser.getNombre());
    }

    @Test
    public void testActualizar() {
        Cliente user = (Cliente) usuarioDAO.findByDni("12345678");
        user.setNombre("John Updated");
        usuarioDAO.actualizar(user);

        Cliente updatedUser = (Cliente) usuarioDAO.findByDni("12345678");
        assertEquals("John Updated", updatedUser.getNombre());
    }

    @Test
    public void testBorrar() {
        Cliente user = (Cliente) usuarioDAO.findByDni("12345678");
        assertNotNull(user);

        usuarioDAO.borrar(user);
        Cliente deletedUser = (Cliente) usuarioDAO.findByDni("12345678");
        assertNull(deletedUser);
    }

    @Test
    public void testExistsByEmail() {
        boolean exists = usuarioDAO.existsByEmail("john.doe@example.com");
        assertTrue(exists);
        
        exists = usuarioDAO.existsByEmail("non.existing@example.com");
        assertFalse(exists);
    }

    @Test
    public void testExistsByDni() {
        boolean exists = usuarioDAO.existsByDni("12345678");
        assertTrue(exists);

        exists = usuarioDAO.existsByDni("00000000");
        assertFalse(exists);
    }

    @Test
    public void testRecuperarTodos() {
        List<Usuario> usuarios = usuarioDAO.recuperarTodos("nombre");
        assertEquals(2, usuarios.size());
    }
}
