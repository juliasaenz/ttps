package ttps.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.spring.model.Comida;
import ttps.spring.model.TipoComida;
import ttps.spring.service.ComidaService;

import java.util.List;

@RestController
@RequestMapping("/comidas")
public class ComidaController {

    @Autowired
    private ComidaService comidaService;

    @PostMapping
    public ResponseEntity<Comida> registrarComida(@RequestBody Comida comida) {
        Comida nuevaComida = comidaService.registrarComida(comida);
        return new ResponseEntity<>(nuevaComida, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Comida>> listarTodas() {
        List<Comida> comidas = comidaService.listarTodas();
        return new ResponseEntity<>(comidas, HttpStatus.OK);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Comida>> buscarPorTipo(@PathVariable TipoComida tipo) {
        List<Comida> comidas = comidaService.buscarPorTipo(tipo);
        return new ResponseEntity<>(comidas, HttpStatus.OK);
    }

    @GetMapping("/vegetarianas")
    public ResponseEntity<List<Comida>> listarVegetarianas() {
        List<Comida> comidas = comidaService.listarVegetarianas();
        return new ResponseEntity<>(comidas, HttpStatus.OK);
    }

    @GetMapping("/existe/{nombre}")
    public ResponseEntity<Boolean> existeComidaPorNombre(@PathVariable String nombre) {
        boolean existe = comidaService.existeComidaPorNombre(nombre);
        return new ResponseEntity<>(existe, HttpStatus.OK);
    }

    @GetMapping("/enMenu/{id}")
    public ResponseEntity<Boolean> comidaEnMenu(@PathVariable Long id) {
        boolean enMenu = comidaService.comidaEnMenu(id);
        return new ResponseEntity<>(enMenu, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComida(@PathVariable Long id) {
        comidaService.eliminarComida(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comida> actualizarComida(@PathVariable Long id, @RequestBody Comida comida) {
        comida.setId(id);
        Comida comidaActualizada = comidaService.actualizarComida(comida);
        return new ResponseEntity<>(comidaActualizada, HttpStatus.OK);
    }
}
    
}
