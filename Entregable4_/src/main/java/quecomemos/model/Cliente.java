package quecomemos.model;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

    public Compra getCompraDia(Date fecha) {
        for (Compra compra : compras) {
            if (compra.getFechaMenu().equals(fecha)) {
                return compra;
            }
        }
        return null;
    }
}
