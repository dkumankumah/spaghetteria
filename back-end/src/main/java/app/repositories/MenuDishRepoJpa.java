package app.repositories;

import app.models.Contact;
import app.models.Menu;
import app.models.MenuDish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Primary
@org.springframework.stereotype.Repository("MENUDISH.JPA")
@Transactional
public class MenuDishRepoJpa {

  @Autowired
  EntityManager entityManager;

  public List<MenuDish> findAllMenus(long id) {
    TypedQuery<MenuDish> query = this.entityManager.createNamedQuery("find_all_menus_with_dish", MenuDish.class);
    return query.setParameter(1,id).getResultList();
  }

  public boolean delete(long id) {
    MenuDish toRemove = entityManager.find(MenuDish.class, id);
    entityManager.remove(toRemove);
    return true;
  }
}
