package ttps.spring.jpa;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.spring.dao.CartaDAO;
import ttps.spring.model.Carta;
import ttps.spring.model.Menu;

@Repository
public class CartaJPA extends GenericJPA<Carta> implements CartaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public CartaJPA() {
        super(Carta.class);
    }

    @Override
    @Transactional
    public Carta actualizar(Carta entity) {
        return entityManager.merge(entity);
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
