package ttps.clasificados;

public class Administrador extends Usuario {
	
	public Administrador(String u, String p) {
		super(u, p);
		super.options.add("Listar Usuarios Publicadores");
		super.options.add("ABM Administradores,");
		super.options.add("Ver Estadísticas");
	}

	@Override
	public String redirect() {
		// TODO Auto-generated method stub
		return "./administrador.html";
	}
	
}
