package ttps.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.spring.model.Sugerencia;
import ttps.spring.model.TipoSugerencia;
import ttps.spring.service.SugerenciaService;

import java.util.List;

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

