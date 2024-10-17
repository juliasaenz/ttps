package quecomemos.dao;

import quecomemos.model.Comida;
import quecomemos.model.TipoComida;
import java.util.List;

public interface ComidaDAO extends GenericDAO<Comida> {

    List<Comida> findByTipo(TipoComida tipo);

    List<Comida> findVegetarian(boolean vegetariano);
    
    public boolean existsByNombre(String nombre);
    
    public boolean isComidaInMenu(Long comidaId);
}
