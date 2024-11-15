package ttps.spring.dao;

import java.util.List;

import ttps.spring.model.Menu;

public interface MenuDAO extends GenericDAO<Menu> {

    List<Menu> getMenusVegetarianos();

    boolean isMenuInCarta();

}
