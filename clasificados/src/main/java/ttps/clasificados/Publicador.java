package ttps.clasificados;

public class Publicador extends Usuario {
	
	public Publicador(String u, String p) {
		super(u, p);
		super.options.add("Actualizar Datos de Contacto");
		super.options.add("ABM de Publicaciones");
		super.options.add("Contestar Consultas");
	}

	@Override
	public String redirect() {
		// TODO Auto-generated method stub
		return "./publicador.html";
	}
	

}
