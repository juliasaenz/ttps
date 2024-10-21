package quecomemos.dao;

import java.util.Date;
import java.util.List;

import quecomemos.model.Compra;

public interface CompraDAO extends GenericDAO<Compra> {
    public List<Compra> verComprasDia(Date semana);  
    public List<Compra> verComprasDeCliente(Long clienteId);
    public List<Compra> verComprasDeMenu(Long menuId);
    public boolean clienteComproParaDia(Long clienteId, Date dia);
}
