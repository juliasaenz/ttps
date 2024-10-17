package quecomemos.dao;

import quecomemos.model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {
    
    Usuario findByEmail(String email);
    
    Usuario findByDni(String dni);
    
    Usuario autenticar(String dni, String clave);
}
