package ttps.spring.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.PersistenceContext;


import ttps.spring.dao.MenuDAO;
import ttps.spring.model.Menu;

@Repository
public class MenuJPA extends GenericJPA<Menu> implements MenuDAO {

    @PersistenceContext
    private EntityManager entityManager; // Spring injects the EntityManager

    public MenuJPA() {
        super(Menu.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Menu> getMenusVegetarianos() {
        TypedQuery<Menu> query = entityManager.createQuery(
                "SELECT m FROM Menu m JOIN m.comidas c " +
                        "GROUP BY m.id " +
                        "HAVING COUNT(CASE WHEN c.vegetariano = false THEN 1 END) = 0",
                Menu.class
        );
        return query.getResultList(); // Return the list of vegetarian menus
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isMenuInCarta() {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(m) FROM Menu m", Long.class
        );
        long count = query.getSingleResult();
        return count > 0; // Check if there are any menus in the carta
    }

    @Override
    @Transactional
    public void borrar(Menu menu) {
        Menu menuABorrar = entityManager.find(Menu.class, menu.getId());
        if (menuABorrar != null) {
            entityManager.remove(menuABorrar); // Remove the menu if it exists
        }
    }

    @Override
    @Transactional
    public Menu persistir(Menu menu) {
        if (menu.getComidas().isEmpty()) {
            throw new IllegalArgumentException("Un menú debe tener al menos una comida.");
        }
        return super.persistir(menu); // Persist the menu using the superclass method
    }
}
