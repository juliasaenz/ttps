package ttps.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.spring.model.Responsable;
import ttps.spring.service.ResponsableService;

import java.util.List;

@RestController
@RequestMapping("/responsables")
public class ResponsableController{

    @Autowired
    private ResponsableService responsableService;

    @PostMapping("/registrar")
    public ResponseEntity<Responsable> registrarResponsable(@RequestBody Responsable responsable) {
        Responsable nuevoResponsable = responsableService.registrarResponsable(responsable);
        return new ResponseEntity<>(nuevoResponsable, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Responsable> actualizarResponsable(@PathVariable Long id, @RequestBody Responsable responsable) {
        responsable.setId(id);
        Responsable responsableActualizado = responsableService.actualizarUsuario(responsable);
        return new ResponseEntity<>(responsableActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResponsable(@PathVariable Long id) {
        responsableService.eliminarResponsable(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Responsable>> listarResponsables() {
        List<Responsable> responsables = responsableService.listarResponsables();
        return new ResponseEntity<>(responsables, HttpStatus.OK);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Responsable> buscarPorDni(@PathVariable String dni) {
        Responsable responsable = responsableService.buscarPorDni(dni);
        return responsable != null ? new ResponseEntity<>(responsable, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/turno/{turno}")
    public ResponseEntity<List<Responsable>> listarPorTurno(@PathVariable String turno) {
        List<Responsable> responsables = responsableService.listarPorTurno(turno);
        return new ResponseEntity<>(responsables, HttpStatus.OK);
    }
}
