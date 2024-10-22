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

import quecomemos.dao.AdministradorDAO;
import quecomemos.dao.ClienteDAO;
import quecomemos.model.Administrador;
import quecomemos.model.Cliente;
import quecomemos.util.EMF;

@TestInstance(Lifecycle.PER_CLASS)
public class AdministradorDAO_JPATest {

	private AdministradorDAO administradorDao;
	private EntityManager em;

	@BeforeEach
	public void setUp() throws Exception {
		administradorDao = new AdministradorDAO_JPA();
		em = EMF.getEMF().createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Administrador admin1 = new Administrador("12345678", "password1", "John", "Doe", "john.doe@example.com");
		Administrador admin2 = new Administrador("87654321", "password2", "Jane", "Doe", "jane.doe@example.com");
		em.persist(admin1);
		em.persist(admin2);

		tx.commit();
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.getTransaction().begin();
		em.createQuery("DELETE FROM Administrador").executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void testFindByEmail() {
		Administrador admin = administradorDao.findByEmail("john.doe@example.com");
		assertNotNull(admin);
		assertEquals("John", admin.getNombre());
	}

	@Test
	public void testFindByDni() {
		Administrador admin = administradorDao.findByDni("87654321");
		assertNotNull(admin);
		assertEquals("Jane", admin.getNombre());
	}

	@Test
	public void testAutenticar() {
		Administrador admin = administradorDao.autenticar("12345678", "password1");
		assertNotNull(admin);
		assertEquals("John", admin.getNombre());
	}

	@Test
	public void testPersistir() {
		Administrador newAdmin = new Administrador("11223344", "password3", "Mike", "Smith", "mike.smith@example.com");
		administradorDao.persistir(newAdmin);

		Administrador foundAdmin = administradorDao.findByEmail("mike.smith@example.com");
		assertNotNull(foundAdmin);
		assertEquals("Mike", foundAdmin.getNombre());
	}

	@Test
	public void testActualizar() {
		Administrador admin = administradorDao.findByDni("12345678");
		admin.setNombre("John Updated");
		administradorDao.actualizar(admin);

		Administrador updatedAdmin = administradorDao.findByDni("12345678");
		assertEquals("John Updated", updatedAdmin.getNombre());
	}

	@Test
	public void testBorrar() {
		Administrador admin = administradorDao.findByDni("12345678");
		assertNotNull(admin);

		administradorDao.borrar(admin);
		Administrador deletedAdmin = administradorDao.findByDni("12345678");
		assertNull(deletedAdmin);
	}

	@Test
	public void testExistsByEmail() {
		boolean exists = administradorDao.existsByEmail("john.doe@example.com");
		assertTrue(exists);

		exists = administradorDao.existsByEmail("non.existing@example.com");
		assertFalse(exists);
	}

	@Test
	public void testExistsByDni() {
		boolean exists = administradorDao.existsByDni("12345678");

		exists = administradorDao.existsByEmail("non.existing@example.com");
		assertFalse(exists);
	}
	
	@Test
	public void testRecuperarTodos() {
	    ClienteDAO clienteDao = new ClienteDAO_JPA();
	    Cliente cliente = new Cliente("234778998", "password1", "John", "Doe", "john2.doe@example.com");
	    clienteDao.persistir(cliente);

	    List<Administrador> administradores = administradorDao.recuperarTodos("nombre");
	    assertEquals(2, administradores.size());

	    for (Administrador admin : administradores) {
	        assertTrue(admin instanceof Administrador);
	    }
	}
}