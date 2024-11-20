package ttps.spring.model;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "Compras")
@Component
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private Date fecha;

    @OneToOne
    @JoinColumn
    private Menu menu;

    public Compra(Cliente cliente, Date fecha, Menu menu) {
        super();
        this.cliente = cliente;
        this.fecha = fecha;
        this.menu = menu;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public Date getFecha() {
        return fecha;
    }
    public Menu getMenu() {
        return menu;
    }
    public Long getId() {
        return id;
    }





}