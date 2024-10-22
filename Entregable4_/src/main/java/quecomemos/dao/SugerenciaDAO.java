package quecomemos.dao;

import java.util.List;

import quecomemos.model.Sugerencia;
import quecomemos.util.TipoSugerencia;

public interface SugerenciaDAO extends GenericDAO<Sugerencia> {
    public List<Sugerencia> sugerenciasPorTipo(TipoSugerencia tipo);
}