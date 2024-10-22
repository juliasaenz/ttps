package quecomemos.dao;

import quecomemos.model.Usuario;

public interface UsuarioDAO<T extends Usuario> extends GenericDAO<T> {

    T findByEmail(String email);

    T findByDni(String dni);

    T autenticar(String dni, String clave);

    boolean existsByEmail(String email);

    boolean existsByDni(String dni);
}
