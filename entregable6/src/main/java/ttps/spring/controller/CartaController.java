package ttps.spring.controller;

import java.time.LocalDate;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import ttps.spring.model.Carta;
import ttps.spring.model.Menu;
import ttps.spring.service.CartaService;

@RestController
@CrossOrigin
@RequestMapping("/cartas")
public class CartaController {

    @Autowired
    private CartaService cartaService;

    @PostMapping
    public ResponseEntity<Carta> registrarCarta(@RequestBody Carta carta) {
        Carta nuevaCarta = cartaService.registrarCarta(carta);
        return new ResponseEntity<>(nuevaCarta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Carta>> listarCartas() {
        List<Carta> cartas = cartaService.listarCartas();
        return new ResponseEntity<>(cartas, HttpStatus.OK);
    }

    @GetMapping("/{dia}")
    public ResponseEntity<Carta> obtenerCartaDia(@PathVariable LocalDate dia) {
        Carta carta = cartaService.obtenerCartaDia(dia);
        return carta != null ? new ResponseEntity<>(carta, HttpStatus.OK)
                             : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/semana")
    public ResponseEntity<List<Carta>> obtenerCartasSemana(@RequestParam Date fechaInicio) {
        List<Carta> cartasSemana = cartaService.obtenerCartasSemana(fechaInicio);
        return new ResponseEntity<>(cartasSemana, HttpStatus.OK);
    }

    @GetMapping("/menus/{dia}")
    public ResponseEntity<List<Menu>> obtenerMenusDia(@PathVariable Date dia) {
        List<Menu> menus = cartaService.obtenerMenusDia(dia);
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @GetMapping("/menus-veggie/{dia}")
    public ResponseEntity<Menu> obtenerMenuVeggieDia(@PathVariable Date dia) {
        Menu menuVeggie = cartaService.obtenerMenuVeggieDia(dia);
        return menuVeggie != null ? new ResponseEntity<>(menuVeggie, HttpStatus.OK)
                                  : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarCarta(@PathVariable Long id, @RequestBody Carta carta) {
        carta.setId(id);
        cartaService.actualizarCarta(carta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarta(@PathVariable Long id) {
        cartaService.eliminarCarta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

