package ttps.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.spring.model.Usuario;
import ttps.spring.service.UsuarioService;
import java.util.Map;
import ttps.spring.service.TokenService;

public abstract class UsuarioController<T extends Usuario> {

    private final UsuarioService<T> usuarioService;

    @Autowired
	private TokenService tokenService;

    public UsuarioController(UsuarioService<T> usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<T> registrarUsuario(@RequestBody T usuario) {
        T nuevoUsuario = usuarioService.registrarUsuario(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> actualizarUsuario(@PathVariable Long id, @RequestBody T usuario) {
        usuario.setId(id);
        T usuarioActualizado = usuarioService.actualizarUsuario(usuario);
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<T>> listarUsuarios() {
        List<T> usuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<T> buscarPorDni(@PathVariable String dni) {
        T usuario = usuarioService.buscarPorDni(dni);
        return usuario != null ? new ResponseEntity<>(usuario, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/login")
public ResponseEntity<Map<String, String>> autenticar(@RequestBody Map<String, String> credentials) {
    int segundosToken = 86400; // 24 horas
    String email = credentials.get("email");
    String password = credentials.get("password");

    T usuario = usuarioService.autenticarUsuario(email, password);

    if (usuario != null) {
        String token = this.tokenService.generarToken(usuario.getEmail(), segundosToken);

        Map<String, String> response = Map.of("token", token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}



    
}
