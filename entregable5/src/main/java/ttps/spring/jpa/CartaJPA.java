package ttps.spring.jpa;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ttps.spring.dao.CartaDAO;
import ttps.spring.dao.MenuDAO;
import ttps.spring.model.Carta;
import ttps.spring.model.Menu;

@Repository
public class CartaJPA extends GenericJPA<Carta> implements CartaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MenuDAO menuDAO;

    public CartaJPA() {
        super(Carta.class);
    }

    private Menu recuperarMenu(Menu m) {
    	if(!menuDAO.existe(m.getId())) {
    		throw new IllegalArgumentException("Menu con ID " + m.getId() + " no existe");
    	}
    	return menuDAO.recuperar(m.getId());
    }

    @Override
    @Transactional
    public Carta persistir(Carta carta) {
    	if (carta.getMenu() == null || carta.getMenuVeggie() == null) {
            throw new IllegalArgumentException("Una carta debe tener un menú regular y uno vegetariano.");
        }
    	carta.setMenu(this.recuperarMenu(carta.getMenu()));
    	carta.setMenusVeggie(this.recuperarMenu(carta.getMenuVeggie()));
    	return super.persistir(carta);
    }

    @Override
    @Transactional
    public Carta actualizar(Carta carta) {
    	carta.setMenu(this.recuperarMenu(carta.getMenu()));
    	carta.setMenusVeggie(this.recuperarMenu(carta.getMenuVeggie()));
        return entityManager.merge(carta);
    }

    @Override
    @Transactional
    public void borrar(Carta entity) {
        if (!entityManager.contains(entity)) {
            entity = entityManager.merge(entity);
        }
        entityManager.remove(entity);
    }

    @Override
    @Transactional
    public Carta borrar(Long id) {
        Carta entity = entityManager.find(Carta.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
        return entity;
    }

    @Override
    public boolean existe(Long id) {
        Carta entity = entityManager.find(Carta.class, id);
        return entity != null;
    }

    @Override
    public Carta recuperar(Serializable id) {
        return entityManager.find(Carta.class, id);
    }

    @Override
    public List<Carta> recuperarTodos() {
        TypedQuery<Carta> query = entityManager.createQuery("SELECT c FROM Carta c ORDER BY c.dia", Carta.class);
        return query.getResultList();
    }

    @Override
    public Carta getCartaDia(LocalDate d) {
        TypedQuery<Carta> query = entityManager.createQuery(
                "SELECT c FROM Carta c WHERE c.dia = :dia", Carta.class
        );
        query.setParameter("dia", d);
        return query.getSingleResult();
    }

    @Override
    public List<Carta> getCartaSemana(Date d) {
        TypedQuery<Carta> query = entityManager.createQuery(
                "SELECT c FROM Carta c WHERE c.dia BETWEEN :startDate AND :endDate", Carta.class
        );
        query.setParameter("startDate", d);
        query.setParameter("endDate", new Date(d.getTime() + (7 * 24 * 60 * 60 * 1000))); // 7 days after
        return query.getResultList();
    }

    @Override
    public List<Menu> getMenusDia(Date dia) {
        TypedQuery<Menu> query = entityManager.createQuery(
                "SELECT c.menu FROM Carta c WHERE c.dia = :dia", Menu.class
        );
        query.setParameter("dia", dia);
        return query.getResultList();
    }

    @Override
    public Menu getMenuVeggieDia(Date dia) {
        TypedQuery<Menu> query = entityManager.createQuery(
                "SELECT c.menuVeggie FROM Carta c WHERE c.dia = :dia", Menu.class
        );
        query.setParameter("dia", dia);
        return query.getSingleResult();
    }
}
