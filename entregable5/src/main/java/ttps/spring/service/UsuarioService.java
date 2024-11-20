package ttps.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttps.spring.dao.UsuarioDAO;
import ttps.spring.model.Usuario;

import java.util.List;

@Service 
public class UsuarioService {

    @Autowired
    private UsuarioDAO<Usuario> usuarioDAO;

    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {
        return usuarioDAO.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorDni(String dni) {
        return usuarioDAO.findByDni(dni);
    }

    @Transactional
    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioDAO.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        if (usuarioDAO.existsByDni(usuario.getDni())) {
            throw new IllegalArgumentException("El DNI ya está registrado");
        }
        return usuarioDAO.persistir(usuario);
    }

    @Transactional
    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioDAO.actualizar(usuario);
    }

    @Transactional
    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioDAO.recuperar(id);
        if (usuario != null) {
            usuarioDAO.borrar(usuario);
        } else {
            throw new IllegalArgumentException("El usuario no existe");
        }
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return usuarioDAO.recuperarTodos("nombre");
    }

    @Transactional(readOnly = true)
    public Usuario autenticarUsuario(String dni, String clave) {
        return usuarioDAO.autenticar(dni, clave);
    }
}
