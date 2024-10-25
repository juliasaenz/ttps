package quecomemos.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {

	private Cliente usuario;

	@BeforeEach
	public void setUp() {
		usuario = new Cliente("12345678", "password123", "Juan", "Perez", "juan.perez@example.com");
	}

	@Test
	public void testGetters() {
		assertEquals("12345678", usuario.getDni());
		assertEquals("password123", usuario.getClave());
		assertEquals("Juan", usuario.getNombre());
		assertEquals("Perez", usuario.getApellido());
		assertEquals("juan.perez@example.com", usuario.getEmail());
	}

	@Test
	public void testSetters() {
		usuario.setDni("87654321");
		usuario.setClave("newpassword");
		usuario.setNombre("Carlos");
		usuario.setApellido("Gomez");
		usuario.setEmail("carlos.gomez@example.com");
		usuario.setFotoUrl("newfoto.jpg");

		assertEquals("87654321", usuario.getDni());
		assertEquals("newpassword", usuario.getClave());
		assertEquals("Carlos", usuario.getNombre());
		assertEquals("Gomez", usuario.getApellido());
		assertEquals("carlos.gomez@example.com", usuario.getEmail());
		assertEquals("newfoto.jpg", usuario.getFotoUrl());
	}

	@Test
	public void testAutenticar() {
		assertTrue(usuario.autenticar("12345678", "password123"));
	}
}
