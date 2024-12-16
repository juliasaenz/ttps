package ttps.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ttps.spring.dao.MenuDAO;
import ttps.spring.model.Menu;

@Service
public class MenuService {

    @Autowired
    private MenuDAO menuDAO;

    @Transactional
    public Menu registrarMenu(Menu menu) {
        return menuDAO.persistir(menu);
    }

    @Transactional(readOnly = true)
    public List<Menu> listarMenus() {
        return menuDAO.recuperarTodos("id");
    }

    @Transactional(readOnly = true)
    public List<Menu> listarMenusVegetarianos() {
        return menuDAO.getMenusVegetarianos();
    }

    @Transactional
    public Menu actualizarMenu(Menu menu) {
        return menuDAO.actualizar(menu);
    }

    @Transactional
    public void eliminarMenu(Long id) {
        Menu menu = menuDAO.recuperar(id);
        if (menu != null) {
            menuDAO.borrar(menu);
        } else {
            throw new IllegalArgumentException("El menú no existe");
        }
    }

    @Transactional(readOnly = true)
    public boolean isMenuInCarta(Long menuId) {
        return menuDAO.isMenuInCarta();
    }
}

