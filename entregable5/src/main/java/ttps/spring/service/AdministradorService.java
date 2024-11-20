package ttps.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttps.spring.dao.AdministradorDAO;
import ttps.spring.model.Administrador;

import java.util.List;

@Service
public class AdministradorService extends UsuarioService<Administrador>{

    @Autowired
    private AdministradorDAO administradorDAO;

    @Transactional
    public Administrador registrarAdministrador(Administrador administrador) {
        if (administradorDAO.existsByEmail(administrador.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        if (administradorDAO.existsByDni(administrador.getDni())) {
            throw new IllegalArgumentException("El DNI ya está registrado");
        }
        return administradorDAO.persistir(administrador);
    }

    @Transactional
    public void eliminarAdministrador(Long id) {
        Administrador administrador = administradorDAO.recuperar(id);
        if (administrador != null) {
            administradorDAO.borrar(administrador);
        } else {
            throw new IllegalArgumentException("El administrador no existe");
        }
    }

    @Transactional(readOnly = true)
    public List<Administrador> listarAdministradores() {
        return administradorDAO.recuperarTodos("nombre");
    }
}

