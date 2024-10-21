package quecomemos.factory;

import quecomemos.dao.AdministradorDAO;
import quecomemos.dao.CartaDAO;
import quecomemos.dao.ClienteDAO;
import quecomemos.dao.ComidaDAO;
import quecomemos.dao.CompraDAO;
import quecomemos.dao.MenuDAO;
import quecomemos.dao.ResponsableDAO;
import quecomemos.dao.SugerenciaDAO;
import quecomemos.dao.UsuarioDAO;
import quecomemos.jpa.AdministradorDAO_JPA;
import quecomemos.jpa.CartaDAO_JPA;
import quecomemos.jpa.ClienteDAO_JPA;
import quecomemos.jpa.ComidaDAO_JPA;
import quecomemos.jpa.CompraDAO_JPA;
import quecomemos.jpa.MenuDAO_JPA;
import quecomemos.jpa.ResponsableDAO_JPA;
import quecomemos.jpa.SugerenciaDAO_JPA;
import quecomemos.jpa.UsuarioDAO_JPA;

public class DaoFactory {
    
    public UsuarioDAO createUsuarioDao() {
    	return new UsuarioDAO_JPA();
    }
	
	public ClienteDAO createClienteDao() {
        return new ClienteDAO_JPA();
    }
	
	public ResponsableDAO createResponsableDao() {
		return new ResponsableDAO_JPA();
	}
	
	public AdministradorDAO createAdministradorDao() {
		return new AdministradorDAO_JPA();
	}
    
    public ComidaDAO createComidaDao() {
        return new ComidaDAO_JPA();
    }
    
    public MenuDAO crateMenuDao() {
    	return new MenuDAO_JPA();
    }
    
    public CartaDAO createCartaDao() {
    	return new CartaDAO_JPA();
    }
    
    public CompraDAO createCompraDao() {
    	return new CompraDAO_JPA();
    }
    
    public SugerenciaDAO createSugerenciaDao() {
    	return new SugerenciaDAO_JPA();
    }
}
