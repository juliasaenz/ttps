package ttps.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttps.spring.dao.CartaDAO;
import ttps.spring.model.Carta;
import ttps.spring.model.Menu;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class CartaService {

    @Autowired
    private CartaDAO cartaDAO;

    @Transactional
    public Carta registrarCarta(Carta carta) {
        return cartaDAO.persistir(carta);
    }

    @Transactional(readOnly = true)
    public List<Carta> listarCartas() {
        return cartaDAO.recuperarTodos();
    }

    @Transactional(readOnly = true)
    public Carta obtenerCartaDia(LocalDate dia) {
        return cartaDAO.getCartaDia(dia);
    }

    @Transactional(readOnly = true)
    public List<Carta> obtenerCartasSemana(Date fechaInicio) {
        return cartaDAO.getCartaSemana(fechaInicio);
    }

    @Transactional(readOnly = true)
    public List<Menu> obtenerMenusDia(Date dia) {
        return cartaDAO.getMenusDia(dia);
    }

    @Transactional(readOnly = true)
    public Menu obtenerMenuVeggieDia(Date dia) {
        return cartaDAO.getMenuVeggieDia(dia);
    }

    @Transactional
    public void actualizarCarta(Carta carta) {
        cartaDAO.actualizar(carta);
    }

    @Transactional
    public void eliminarCarta(Long id) {
        Carta carta = cartaDAO.recuperar(id);
        if (carta != null) {
            cartaDAO.borrar(carta);
        } else {
            throw new IllegalArgumentException("La carta no existe");
        }
    }
}

