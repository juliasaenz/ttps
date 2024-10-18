package ttps.clasificados;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
	private String usuario;
	private String password; 
	protected List<String> options = new ArrayList<String>();
	
	public Usuario(String u, String p) {
		this.usuario = u;
		this.password = p;
	}
	
	public boolean userIsValid(String incomingUsername, String incomingPassword) {
		return this.usuario.equals(incomingUsername) && 
				this.password.equals(incomingPassword);
	}
	

	public abstract String redirect();

	public String getUsuario() {
		return usuario;
	}

	public String getPassword() {
		return password;
	}

	public List<String> getOptions() {
		return options;
	}
}
