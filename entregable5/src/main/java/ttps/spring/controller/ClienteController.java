package ttps.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.spring.model.Cliente;
import ttps.spring.service.ClienteService;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/registrar")
    public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.registrarCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        Cliente clienteActualizado = clienteService.actualizarCliente(cliente);
        return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Cliente> buscarPorDni(@PathVariable String dni) {
        Cliente cliente = clienteService.buscarPorDni(dni);
        return cliente != null ? new ResponseEntity<>(cliente, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/vegetariano")
    public ResponseEntity<Boolean> isVegetariano(@PathVariable Long id) {
        boolean vegetariano = clienteService.isClienteVegetariano(id);
        return new ResponseEntity<>(vegetariano, HttpStatus.OK);
    }

   /*@GetMapping("/{id}/compras/{fecha}")
    public ResponseEntity<Cliente> obtenerCompraDelDia(@PathVariable Long id, @PathVariable Date fecha) {
        Cliente cliente = clienteService.getCompraDelDia(id, fecha);
        return cliente != null ? new ResponseEntity<>(cliente, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/
}
