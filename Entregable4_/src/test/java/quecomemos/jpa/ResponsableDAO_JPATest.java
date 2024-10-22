package quecomemos.jpa;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import quecomemos.dao.ClienteDAO;
import quecomemos.dao.ResponsableDAO;
import quecomemos.model.Cliente;
import quecomemos.model.Responsable;
import quecomemos.util.EMF;

@TestInstance(Lifecycle.PER_CLASS)
public class ResponsableDAO_JPATest {

    private ResponsableDAO responsableDao;
    private EntityManager em;

    @BeforeEach
    public void setUp() throws Exception {
        responsableDao = new ResponsableDAO_JPA();
        em = EMF.getEMF().createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Responsable responsable1 = new Responsable("12345678", "password1", "John", "Doe", "john.doe@example.com");
        responsable1.setTurno("tarde");
        Responsable responsable2 = new Responsable("87654321", "password2", "Jane", "Doe", "jane.doe@example.com");
        responsable2.setTurno("tarde");
        em.persist(responsable1);
        em.persist(responsable2);

        tx.commit();
    }

    @AfterEach
    public void tearDown() throws Exception {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Responsable").executeUpdate();
        em.createQuery("DELETE FROM Usuario").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testFindByEmail() {
        Responsable user = responsableDao.findByEmail("john.doe@example.com");
        assertNotNull(user);
        assertEquals("John", user.getNombre());
    }

    @Test
    public void testFindByDni() {
        Responsable user = responsableDao.findByDni("87654321");
        assertNotNull(user);
        assertEquals("Jane", user.getNombre());
    }

    @Test
    public void testAutenticar() {
        Responsable user = responsableDao.autenticar("12345678", "password1");
        assertNotNull(user);
        assertEquals("John", user.getNombre());
    }

    @Test
    public void testPersistir() {
        Responsable newUser = new Responsable("11223344", "password3", "Mike", "Smith", "mike.smith@example.com");
        newUser.setTurno("tarde");
        responsableDao.persistir(newUser);

        Responsable foundUser = responsableDao.findByEmail("mike.smith@example.com");
        assertNotNull(foundUser);
        assertEquals("Mike", foundUser.getNombre());
    }

    @Test
    public void testActualizar() {
        Responsable user = responsableDao.findByDni("12345678");
        user.setNombre("John Updated");
        responsableDao.actualizar(user);

        Responsable updatedUser = responsableDao.findByDni("12345678");
        assertEquals("John Updated", updatedUser.getNombre());
    }

    @Test
    public void testBorrar() {
        Responsable user = responsableDao.findByDni("12345678");
        assertNotNull(user);

        responsableDao.borrar(user);
        Responsable deletedUser = responsableDao.findByDni("12345678");
        assertNull(deletedUser);
    }

    @Test
    public void testExistsByEmail() {
        boolean exists = responsableDao.existsByEmail("john.doe@example.com");
        assertTrue(exists);

        exists = responsableDao.existsByEmail("non.existing@example.com");
        assertFalse(exists);
    }

    @Test
    public void testExistsByDni() {
        boolean exists = responsableDao.existsByDni("12345678");
        assertTrue(exists);

        exists = responsableDao.existsByDni("00000000");
        assertFalse(exists);
    }

    @Test
    public void testRecuperarTodos() {
    	ClienteDAO clienteDao = new ClienteDAO_JPA();
    	Cliente newUser = new Cliente("124554546", "password3", "Mike", "Smith", "mike.smith@example.com");
        clienteDao.persistir(newUser);
        
        List<Responsable> responsables = responsableDao.recuperarTodos("nombre");
        assertEquals(2, responsables.size());
    }


}
