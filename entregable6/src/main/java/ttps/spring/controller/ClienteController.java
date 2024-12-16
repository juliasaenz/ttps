package ttps.spring.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.model.Cliente;
import ttps.spring.model.Compra;
import ttps.spring.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController extends UsuarioController<Cliente>{

    @Autowired
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        super(clienteService);
        this.clienteService = clienteService;
    }

    @GetMapping("/{id}/vegetariano")
    public ResponseEntity<Boolean> isVegetariano(@PathVariable Long id) {
        boolean vegetariano = clienteService.isClienteVegetariano(id);
        return new ResponseEntity<>(vegetariano, HttpStatus.OK);
    }

   @GetMapping("/{id}/compras/{fecha}")
    public ResponseEntity<Compra> obtenerCompraDelDia(@PathVariable Long id, @PathVariable Date fecha) {
        Compra compra = clienteService.getCompraDelDia(id, fecha);
        return compra != null ? new ResponseEntity<>(compra, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
