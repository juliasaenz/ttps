package ttps.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.model.Administrador;
import ttps.spring.service.AdministradorService;

@RestController
@RequestMapping("/administradores")
public class AdministradorController extends UsuarioController<Administrador>{

    @Autowired
    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        super(administradorService);
        this.administradorService = administradorService;
    }
}
