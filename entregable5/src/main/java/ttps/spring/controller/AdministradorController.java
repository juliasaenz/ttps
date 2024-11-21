package ttps.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.model.Administrador;
import ttps.spring.service.AdministradorService;

@RestController
@RequestMapping("/administradores")
public class AdministradorController{

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
