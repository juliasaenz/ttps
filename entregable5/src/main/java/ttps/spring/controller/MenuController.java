package ttps.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.spring.model.Menu;
import ttps.spring.service.MenuService;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseEntity<Menu> registrarMenu(@RequestBody Menu menu) {
        Menu nuevoMenu = menuService.registrarMenu(menu);
        return new ResponseEntity<>(nuevoMenu, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Menu>> listarMenus() {
        List<Menu> menus = menuService.listarMenus();
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @GetMapping("/vegetarianos")
    public ResponseEntity<List<Menu>> listarMenusVegetarianos() {
        List<Menu> menus = menuService.listarMenusVegetarianos();
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Menu> actualizarMenu(@PathVariable Long id, @RequestBody Menu menu) {
        menu.setId(id);
        Menu m = menuService.actualizarMenu(menu);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMenu(@PathVariable Long id) {
        menuService.eliminarMenu(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

