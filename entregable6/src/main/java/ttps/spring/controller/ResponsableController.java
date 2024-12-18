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
import org.springframework.web.bind.annotation.CrossOrigin;

import ttps.spring.model.Responsable;
import ttps.spring.service.ResponsableService;

@RestController
@CrossOrigin
@RequestMapping("/responsables")
public class ResponsableController extends UsuarioController<Responsable>{

    @Autowired
    private final ResponsableService responsableService;
    
    public ResponsableController(ResponsableService responsableService) {
    	super(responsableService);
    	this.responsableService = responsableService;
    }

    @GetMapping("/turno/{turno}")
    public ResponseEntity<List<Responsable>> listarPorTurno(@PathVariable String turno) {
        List<Responsable> responsables = responsableService.listarPorTurno(turno);
        return new ResponseEntity<>(responsables, HttpStatus.OK);
    }
}
