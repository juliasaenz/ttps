package quecomemos.jpa;

import quecomemos.dao.AdministradorDAO;
import quecomemos.model.Administrador;

public class AdministradorDAO_JPA extends UsuarioDAO_JPA<Administrador> implements AdministradorDAO {

    public AdministradorDAO_JPA() {
        super(Administrador.class);
    }
    

}
