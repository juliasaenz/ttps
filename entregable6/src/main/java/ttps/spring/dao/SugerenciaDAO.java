package ttps.spring.dao;

import java.util.List;

import ttps.spring.model.Sugerencia;
import ttps.spring.model.TipoSugerencia;

public interface SugerenciaDAO extends GenericDAO<Sugerencia> {
    public List<Sugerencia> sugerenciasPorTipo(TipoSugerencia tipo);
}
