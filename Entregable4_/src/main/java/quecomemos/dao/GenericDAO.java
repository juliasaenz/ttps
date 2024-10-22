package quecomemos.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T> {
	T actualizar(T entity);

	void borrar(T entity);

	T borrar(Long id);

	boolean existe(Long id);

	T persistir(T entity);

	T recuperar(Serializable id);

	List<T> recuperarTodos(String column);
}