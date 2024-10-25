package quecomemos.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import quecomemos.dao.MenuDAO;
import quecomemos.model.Menu;
import quecomemos.util.EMF;

public class MenuDAO_JPA extends GenericDAO_JPA<Menu> implements MenuDAO {

    public MenuDAO_JPA() {
        super(Menu.class);
    }

    @Override
    public List<Menu> getMenusVegetarianos() {
    	EntityManager em = EMF.getEMF().createEntityManager();
        TypedQuery<Menu> query = em.createQuery(
            "SELECT m FROM Menu m JOIN m.comidas c " +
            "GROUP BY m.id " +
            "HAVING COUNT(CASE WHEN c.vegetariano = false THEN 1 END) = 0",
            Menu.class
        );
        List<Menu> result = query.getResultList();
        em.close();
        return result;
    }

    @Override
    public boolean isMenuInCarta() {
        EntityManager em = EMF.getEMF().createEntityManager();
        TypedQuery<Long> query = em.createQuery(
            "SELECT COUNT(m) FROM Menu m", Long.class
        );
        long count = query.getSingleResult();
        em.close();
        return count > 0;
    }

    @Override
    public void borrar(Menu menu) {
        EntityManager em = EMF.getEMF().createEntityManager();
        em.getTransaction().begin();
        Menu menuABorrar = em.find(Menu.class, menu.getId());
        if (menuABorrar != null) {
            em.remove(menuABorrar);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Menu persistir(Menu menu) {
        if (menu.getComidas().isEmpty()) {
            throw new IllegalArgumentException("Un menú debe tener al menos una comida.");
        }
        return super.persistir(menu);
    }
}

