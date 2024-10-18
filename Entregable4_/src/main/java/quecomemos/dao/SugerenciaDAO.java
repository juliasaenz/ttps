package quecomemos.dao;

import quecomemos.model.Sugerencia;

public interface SugerenciaDAO extends GenericDAO<Sugerencia> {
    public List<Sugerencia> getTipo(String tipo);
    public List<Sugerencia> getFecha(Date fecha);
}