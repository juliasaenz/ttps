package ttps.spring.dao;

import java.util.List;

import ttps.spring.model.Responsable;

public interface ResponsableDAO extends UsuarioDAO<Responsable> {

    public List<Responsable> getResponsablesbyTurno(String turno);
}
