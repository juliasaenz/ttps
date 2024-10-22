package quecomemos.dao;

import java.util.List;

import quecomemos.model.Menu;

public interface MenuDAO extends GenericDAO<Menu> {

	List<Menu> getMenusVegetarianos();

	boolean isMenuInCarta();

}