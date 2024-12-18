package ttps.spring.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.CrossOrigin;

import ttps.spring.model.Compra;
import ttps.spring.service.CompraService;

@RestController
@CrossOrigin
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping
    public ResponseEntity<Compra> registrarCompra(@RequestBody Compra compra) {
        Compra nuevaCompra = compraService.registrarCompra(compra);
        return new ResponseEntity<>(nuevaCompra, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Compra>> listarCompras() {
        List<Compra> compras = compraService.listarCompras();
        return new ResponseEntity<>(compras, HttpStatus.OK);
    }

    @GetMapping("/dia/{fecha}")
    public ResponseEntity<List<Compra>> verComprasDia(@PathVariable Date fecha) {
        List<Compra> compras = compraService.verComprasDia(fecha);
        return new ResponseEntity<>(compras, HttpStatus.OK);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Compra>> verComprasDeCliente(@PathVariable Long clienteId) {
        List<Compra> compras = compraService.verComprasDeCliente(clienteId);
        return new ResponseEntity<>(compras, HttpStatus.OK);
    }

    @GetMapping("/menu/{menuId}")
    public ResponseEntity<List<Compra>> verComprasDeMenu(@PathVariable Long menuId) {
        List<Compra> compras = compraService.verComprasDeMenu(menuId);
        return new ResponseEntity<>(compras, HttpStatus.OK);
    }

    @GetMapping("/cliente/{clienteId}/dia/{fecha}")
    public ResponseEntity<Boolean> clienteComproParaDia(@PathVariable Long clienteId, @PathVariable Date fecha) {
        boolean resultado = compraService.clienteComproParaDia(clienteId, fecha);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCompra(@PathVariable Long id) {
        compraService.eliminarCompra(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

