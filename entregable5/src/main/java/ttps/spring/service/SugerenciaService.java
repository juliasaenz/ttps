package ttps.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttps.spring.dao.SugerenciaDAO;
import ttps.spring.model.Sugerencia;
import ttps.spring.model.TipoSugerencia;

import java.util.List;

@Service
public class SugerenciaService {

    @Autowired
    private SugerenciaDAO sugerenciaDAO;

    @Transactional
    public Sugerencia registrarSugerencia(Sugerencia sugerencia) {
        return sugerenciaDAO.persistir(sugerencia);
    }

    @Transactional(readOnly = true)
    public List<Sugerencia> listarSugerencias() {
        return sugerenciaDAO.recuperarTodos("fecha");
    }

    @Transactional(readOnly = true)
    public List<Sugerencia> listarSugerenciasPorTipo(TipoSugerencia tipo) {
        return sugerenciaDAO.sugerenciasPorTipo(tipo);
    }

    @Transactional
    public void eliminarSugerencia(Long id) {
        Sugerencia sugerencia = sugerenciaDAO.recuperar(id);
        if (sugerencia != null) {
            sugerenciaDAO.borrar(sugerencia);
        } else {
            throw new IllegalArgumentException("La sugerencia no existe");
        }
    }
}
