package quecomemos.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Clientes")
public class Cliente extends Usuario {
    
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
    private List<Compra> compras = new ArrayList<>();
	
	@Column(nullable = false)
    private boolean vegetariano = false;

    public Cliente(String dni, String clave, String nombre, String apellido, String email) {
        super(dni, clave, nombre, apellido, email);
    }

    public boolean isVegetariano() {
        return vegetariano;
    }

    public void setVegetariano(boolean vegetariano) {
        this.vegetariano = vegetariano;
    }

    public List<Compra> getCompras() {
        return new ArrayList<>(compras);
    }

    public List<Compra> getComprasDia(Date fecha) {
        List<Compra> comprasDelDia = new ArrayList<>();
        for (Compra compra : compras) {
            if (compra.getFecha().equals(fecha)) {
                comprasDelDia.add(compra);
            }
        }
        return comprasDelDia;
    }

    public Compra comprarMenu(Date fecha, Menu menu) {
        Compra nuevaCompra = new Compra(this, fecha, menu);
        this.compras.add(nuevaCompra);
        return nuevaCompra;
    }

    public Sugerencia crearSugerencia(String texto, String tipo) {
        Sugerencia nuevaSugerencia = new Sugerencia(tipo, texto, this);
        return nuevaSugerencia;
    }
}
