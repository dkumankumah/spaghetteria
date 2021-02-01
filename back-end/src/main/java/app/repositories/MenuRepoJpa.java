package app.repositories;

import app.models.Contact;
import app.models.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Primary
@org.springframework.stereotype.Repository("MENU.JPA")
@Transactional
public class MenuRepoJpa implements Repository<Menu>{

  @Autowired
  EntityManager entityManager;

  public List<Menu> findAllContacts(String username) {
    TypedQuery<Menu> query = this.entityManager.createNamedQuery("findAll_menu_with_username", Menu.class);
    return query.setParameter(1, username).getResultList();
  }

  @Override
  public Menu findById(long id) {
    // TypedQuery<Contact> query = this.entityManager.createNamedQuery("find_contact_by_id", Contact.class);
//    TypedQuery<Contact> query = findByQuery("find_contact_by_id",id);
//    return query.setParameter(1,id).getSingleResult();
    return entityManager.find(Menu.class, id);
  }

  @Override
  public List<Menu> findAll() {
    TypedQuery<Menu> query = this.entityManager.createNamedQuery("findAll_menu", Menu.class);
    return query.getResultList();

  }

  @Override
  public boolean delete(Menu contact) {
    Menu toRemove = entityManager.merge(entityManager.find(Menu.class, contact.getId()));
    entityManager.remove(toRemove);
    return false;
  }

  @Override
  public Menu save(Menu contact) {
//    if (contact.getId() == 0) {
//      entityManager.persist(contact);
//    } else {
      entityManager.merge(contact);
//    }
    return contact;
  }

  public List<Menu> findByQuery(String jpqlName, Object... params) {
    TypedQuery<Menu> namedQuery = entityManager.createNamedQuery(jpqlName, Menu.class);
    return namedQuery.setParameter(1, params[0]).getResultList();
  }
}
