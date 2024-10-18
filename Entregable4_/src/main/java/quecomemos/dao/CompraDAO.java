package quecomemos.dao;

import quecomemos.model.Compra;

public interface CompraDAO extends GenericDAO<Compra> {
    public List<Compra> verComprasSemana(Date semana);  
}
