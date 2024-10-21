package quecomemos.dao;

import java.util.List;

import quecomemos.model.Comida;
import quecomemos.util.TipoComida;

public interface ComidaDAO extends GenericDAO<Comida> {

    List<Comida> findByTipo(TipoComida tipo);

    List<Comida> findVegetarian(boolean vegetariano);
    
    public boolean existsByNombre(String nombre);
    
    public boolean isComidaInMenu(Long comidaId);
}
