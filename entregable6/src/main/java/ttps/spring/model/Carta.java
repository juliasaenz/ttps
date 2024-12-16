package ttps.spring.model;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cartas")
@Component
public class Carta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private LocalDate dia;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private Menu menu;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "menu_veggie_id", referencedColumnName = "id")
    private Menu menuVeggie;

    public Carta(LocalDate localDate) {
        super();
        this.dia = localDate;
    }

    public Carta() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id){
    	this.id = id;    }

    public LocalDate getDia() {
        return dia;
    }
    public void setDia(LocalDate dia) {
        this.dia = dia;
    }
    public Menu getMenu() {
        return menu;
    }
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Menu getMenuVeggie() {
        return menuVeggie;
    }
    public void setMenusVeggie(Menu menuVeggie) {
        this.menuVeggie = menuVeggie;
    }
}