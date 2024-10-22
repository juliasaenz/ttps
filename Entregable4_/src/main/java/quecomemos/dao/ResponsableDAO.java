package quecomemos.dao;

import java.util.List;

import quecomemos.model.Responsable;

public interface ResponsableDAO extends UsuarioDAO<Responsable> {

	public List<Responsable> getResponsablesbyTurno(String turno);
}