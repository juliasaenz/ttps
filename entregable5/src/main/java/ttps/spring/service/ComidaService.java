package ttps.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttps.spring.dao.ComidaDAO;
import ttps.spring.model.Comida;
import ttps.spring.model.TipoComida;

import java.util.List;

@Service
public class ComidaService {

    @Autowired
    private ComidaDAO comidaDAO;

    @Transactional(rollbackFor = IllegalArgumentException.class)
    public Comida registrarComida(Comida comida) {
        if (comidaDAO.existsByNombre(comida.getNombre())) {
            throw new IllegalArgumentException("El nombre de la comida ya existe.");
        }
        return comidaDAO.persistir(comida);
    }

    @Transactional(readOnly = true)
    public List<Comida> listarTodas() {
        return comidaDAO.recuperarTodos("nombre");
    }

    @Transactional(readOnly = true)
    public List<Comida> buscarPorTipo(TipoComida tipo) {
        return comidaDAO.findByTipo(tipo);
    }

    @Transactional(readOnly = true)
    public List<Comida> listarVegetarianas() {
        return comidaDAO.findVegetarian(true);
    }

    @Transactional(readOnly = true)
    public boolean existeComidaPorNombre(String nombre) {
        return comidaDAO.existsByNombre(nombre);
    }

    @Transactional(readOnly = true)
    public boolean comidaEnMenu(Long comidaId) {
        return comidaDAO.isComidaInMenu(comidaId);
    }

    @Transactional
    public void eliminarComida(Long id) {
        Comida comida = comidaDAO.recuperar(id);
        if (comida != null) {
            if (comidaDAO.isComidaInMenu(id)) {
                throw new IllegalStateException("La comida está asociada a un menú.");
            }
            comidaDAO.borrar(comida);
        } else {
            throw new IllegalArgumentException("La comida no existe.");
        }
    }

    @Transactional
    public Comida actualizarComida(Comida comida) {
        return comidaDAO.actualizar(comida);
    }
}
