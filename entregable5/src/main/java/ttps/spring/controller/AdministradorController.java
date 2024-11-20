package ttps.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.spring.model.Administrador;
import ttps.spring.service.AdministradorService;

import java.util.List;

@RestController
@RequestMapping("/administradores")
public class AdministradorController extends UsuarioController<Administrador> {

    @Autowired
    private AdministradorService administradorService;

    @PostMapping("/registrar")
    public ResponseEntity<Administrador> registrarAdministrador(@RequestBody Administrador administrador) {
        Administrador nuevoAdministrador = administradorService.registrarAdministrador(administrador);
        return new ResponseEntity<>(nuevoAdministrador, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrador> actualizarAdministrador(@PathVariable Long id, @RequestBody Administrador administrador) {
        administrador.setId(id);
        Administrador administradorActualizado = administradorService.actualizarUsuario(administrador);
        return new ResponseEntity<>(administradorActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAdministrador(@PathVariable Long id) {
        administradorService.eliminarAdministrador(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Administrador>> listarAdministradores() {
        List<Administrador> administradores = administradorService.listarAdministradores();
        return new ResponseEntity<>(administradores, HttpStatus.OK);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Administrador> buscarPorDni(@PathVariable String dni) {
        Administrador administrador = administradorService.buscarPorDni(dni);
        return administrador != null ? new ResponseEntity<>(administrador, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
