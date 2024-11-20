package ttps.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttps.spring.dao.ResponsableDAO;
import ttps.spring.model.Responsable;

import java.util.List;

@Service
public class ResponsableService {

    @Autowired
    private ResponsableDAO responsableDAO;

    @Transactional(readOnly = true)
    public Responsable buscarPorEmail(String email) {
        return responsableDAO.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Responsable buscarPorDni(String dni) {
        return responsableDAO.findByDni(dni);
    }

    @Transactional
    public Responsable registrarResponsable(Responsable responsable) {
        if (responsableDAO.existsByEmail(responsable.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        if (responsableDAO.existsByDni(responsable.getDni())) {
            throw new IllegalArgumentException("El DNI ya está registrado");
        }
        return responsableDAO.persistir(responsable);
    }

    @Transactional
    public Responsable actualizarResponsable(Responsable responsable) {
        return responsableDAO.actualizar(responsable);
    }

    @Transactional
    public void eliminarResponsable(Long id) {
        Responsable responsable = responsableDAO.recuperar(id);
        if (responsable != null) {
            responsableDAO.borrar(responsable);
        } else {
            throw new IllegalArgumentException("El responsable no existe");
        }
    }

    @Transactional(readOnly = true)
    public List<Responsable> listarResponsables() {
        return responsableDAO.recuperarTodos("nombre");
    }

    @Transactional(readOnly = true)
    public List<Responsable> listarPorTurno(String turno) {
        return responsableDAO.getResponsablesbyTurno(turno);
    }
}
