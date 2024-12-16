package ttps.spring.dao;

import java.util.List;

import ttps.spring.model.Comida;
import ttps.spring.model.TipoComida;

public interface ComidaDAO extends GenericDAO<Comida> {

    List<Comida> findByTipo(TipoComida tipo);

    List<Comida> findVegetarian(boolean vegetariano);

    public boolean existsByNombre(String nombre);

    public boolean isComidaInMenu(Long comidaId);
    
    public Comida recuperarPorNombre(String nombre);
}