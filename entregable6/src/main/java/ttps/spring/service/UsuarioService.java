package ttps.spring.service;

import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttps.spring.dao.UsuarioDAO;
import ttps.spring.model.Usuario;

@Service
public abstract class UsuarioService<T extends Usuario> {

    @Autowired
    private UsuarioDAO<T> usuarioDAO;

    @Transactional(readOnly = true)
    public T buscarPorEmail(String email) {
        return usuarioDAO.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public T buscarPorDni(String dni) {
        return usuarioDAO.findByDni(dni);
    }

    @Transactional
    public T registrarUsuario(T usuario) {
        if (usuarioDAO.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        if (usuarioDAO.existsByDni(usuario.getDni())) {
            throw new IllegalArgumentException("El DNI ya está registrado");
        }
        return usuarioDAO.persistir(usuario);
    }

    @Transactional
    public T actualizarUsuario(T usuario) {
        return usuarioDAO.actualizar(usuario);
    }

    @Transactional
    public void eliminarUsuario(Long id) {
        T usuario = usuarioDAO.recuperar(id);
        if (usuario != null) {
            usuarioDAO.borrar(usuario);
        } else {
            throw new IllegalArgumentException("El usuario no existe");
        }
    }

    @Transactional(readOnly = true)
    public List<T> listarUsuarios() {
        return usuarioDAO.recuperarTodos("nombre");
    }

    @Transactional(readOnly = true)
    public T autenticarUsuario(String email, String clave) {
        T usuario = usuarioDAO.findByEmail(email);  
        if (usuario != null && usuario.getClave().equals(clave)) {
            return usuario; 
        }
        return null;  
    }

}
