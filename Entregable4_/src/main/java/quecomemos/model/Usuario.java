package quecomemos.model;

import javax.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuario {
	@Id
	private Long id;

	String dni;
	String clave;
	String nombre;
	String apellido;
	String fotoUrl;
	String email;

	public Usuario(String dni, String clave, String nombre, String apellido, String email) {
		this.dni = dni;
		this.clave = clave;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getFotoUrl() {
		return fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean autenticar(String dni, String clave) {
		return true;
		// TODO implementar
	}

}
