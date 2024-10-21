package quecomemos.dao;

import java.util.Date;
import java.util.List;

import quecomemos.model.Compra;

public interface CompraDAO extends GenericDAO<Compra> {
    public List<Compra> verComprasSemana(Date semana);  
}
