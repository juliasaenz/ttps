package ttps.spring.controller;

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
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.model.Menu;
import ttps.spring.service.MenuService;

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

