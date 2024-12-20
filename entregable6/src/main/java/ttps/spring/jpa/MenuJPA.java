package ttps.spring.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ttps.spring.dao.ComidaDAO;
import ttps.spring.dao.MenuDAO;
import ttps.spring.model.Comida;
import ttps.spring.model.Menu;

@Repository
public class MenuJPA extends GenericJPA<Menu> implements MenuDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ComidaDAO comidaDAO;

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
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isMenuInCarta() {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(m) FROM Menu m", Long.class
        );
        long count = query.getSingleResult();
        return count > 0; 
    }

    @Override
    @Transactional
    public void borrar(Menu menu) {
        Menu menuABorrar = entityManager.find(Menu.class, menu.getId());
        if (menuABorrar != null) {
            entityManager.remove(menuABorrar); 
        }
    }

    private List<Comida> resolverComidas (Menu menu){
    	if (menu.getComidas().isEmpty()) {
            throw new IllegalArgumentException("Un menú debe tener al menos una comida.");
        }

        for (Comida comida : menu.getComidas()) {
            if (!comidaDAO.existe(comida.getId())) {
                throw new IllegalArgumentException("Comida con ID " + comida.getId() + " no existe");
            }
        }

        return menu.getComidas().stream()
        	    .map(comida -> {
        	        return comidaDAO.recuperar(comida.getId());
        	    })
        	    .toList();
    }

    @Override
    @Transactional
    public Menu persistir(Menu menu) {
        menu.setComidas(this.resolverComidas(menu));
        return super.persistir(menu);
    }

    @Override
    @Transactional
    public Menu actualizar(Menu menu) {
        menu.setComidas(this.resolverComidas(menu));
        return super.actualizar(menu);
    }


}
