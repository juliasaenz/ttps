package ttps.clasificados;

public class SitioClasificado {
	protected String nombre;
	protected String email;
	protected String telefono;
	
	public SitioClasificado(String nom, String em, String tel) {
		this.nombre = nom;
		this.email = em;
		this.telefono = tel;
	}

	@Override
	public String toString() {
		return "SitioClasificado [nombre=" + nombre + ", email=" + email + ", telefono=" + telefono + "]";
	}

}
