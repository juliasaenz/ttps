package ttps.spring.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.spring.model.Usuario;
import ttps.spring.service.UsuarioService;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;


import java.util.Date;

public abstract class UsuarioController<T extends Usuario> {

    private final UsuarioService<T> usuarioService;

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
        return usuario != null ? new ResponseEntity<>(usuario, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> autenticar(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        T usuario = usuarioService.autenticarUsuario(email, password);

        if (usuario != null) {
            String token = generateToken(usuario);

            return new ResponseEntity<>(Map.of("token", token, "usuario", usuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

   
   // ...
   
   private String generateToken(T usuario) {
       SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
   
       return Jwts.builder()
               .setSubject(usuario.getEmail())
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis() + 8900000))
               .signWith(secretKey)
               .compact();
   }
}
