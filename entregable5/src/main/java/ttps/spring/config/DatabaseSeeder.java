package ttps.spring.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import ttps.spring.model.Carta;
import ttps.spring.model.Comida;
import ttps.spring.model.Menu;
import ttps.spring.model.TipoComida;
import ttps.spring.service.CartaService;
import ttps.spring.service.ComidaService;
import ttps.spring.service.MenuService;

@Component
public class DatabaseSeeder {

    @Autowired
    private ComidaService comidaService;
    
    @Autowired
    private MenuService menuService;
    
    @Autowired
    private CartaService cartaService;

    @PostConstruct
    public void seedDatabase() {
        try {
            Comida comida1 = new Comida("Pizza", TipoComida.PLATO_PRINCIPAL, true); 
            Comida comida2 = new Comida("Hamburguesa", TipoComida.PLATO_PRINCIPAL, false); 
            Comida comida3 = new Comida("Ensalada", TipoComida.ENTRADA, true); 
            Comida comida4 = new Comida("Gaseosa", TipoComida.BEBIDA, false);
            Comida comida5 = new Comida("Fruta", TipoComida.POSTRE, true);

            comida1 = getOrCreateComida(comida1);
            comida2 = getOrCreateComida(comida2);
            comida3 = getOrCreateComida(comida3);
            comida4 = getOrCreateComida(comida4);
            comida5 = getOrCreateComida(comida5);
            
            Menu menu = new Menu();
            menu.setComidas(List.of(comida1, comida3, comida4)); 
            menu.setPrecio(500);
            menu = menuService.registrarMenu(menu);  
            
            Menu menuVeggie = new Menu();
            menuVeggie.setComidas(List.of(comida2, comida5)); 
            menuVeggie.setPrecio(500);
            menuVeggie = menuService.registrarMenu(menuVeggie); 
            
            Menu menu3 = new Menu();
            menu3.setComidas(List.of(comida1, comida3, comida5, comida4)); 
            menu3.setPrecio(500);
            menu3 = menuService.registrarMenu(menu3); 
            
            Carta carta = new Carta(LocalDate.now());
            carta.setMenu(menu);
            carta.setMenusVeggie(menuVeggie);
            cartaService.registrarCarta(carta);

        } catch (Exception e) {
            System.out.println("Error seeding database: " + e.getMessage());
        }
    }

    private Comida getOrCreateComida(Comida comida) {
        if (!comidaService.existeComidaPorNombre(comida.getNombre())) {
            return comidaService.registrarComida(comida);
        } else {
            return comidaService.recuperarPorNombre(comida.getNombre());
        }
    }

}
