package ttps.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.model.Sugerencia;
import ttps.spring.model.TipoSugerencia;
import ttps.spring.service.SugerenciaService;

@RestController
@RequestMapping("/sugerencias")
public class SugerenciaController {

    @Autowired
    private SugerenciaService sugerenciaService;

    @PostMapping
    public ResponseEntity<Sugerencia> registrarSugerencia(@RequestBody Sugerencia sugerencia) {
        Sugerencia nuevaSugerencia = sugerenciaService.registrarSugerencia(sugerencia);
        return new ResponseEntity<>(nuevaSugerencia, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Sugerencia>> listarSugerencias() {
        List<Sugerencia> sugerencias = sugerenciaService.listarSugerencias();
        return new ResponseEntity<>(sugerencias, HttpStatus.OK);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Sugerencia>> listarSugerenciasPorTipo(@PathVariable TipoSugerencia tipo) {
        List<Sugerencia> sugerencias = sugerenciaService.listarSugerenciasPorTipo(tipo);
        return new ResponseEntity<>(sugerencias, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSugerencia(@PathVariable Long id) {
        sugerenciaService.eliminarSugerencia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

