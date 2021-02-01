package app.repositories;

import app.models.Contact;
import app.models.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Primary
@org.springframework.stereotype.Repository("DISH.JPA")
@Transactional
public class DishRepoJpa implements Repository<Dish>{

  @Autowired
  EntityManager entityManager;

  public List<Dish> findAllContacts(String username) {
    TypedQuery<Dish> query = this.entityManager.createNamedQuery("findAll_dish_with_username", Dish.class);
    return query.setParameter(1, username).getResultList();
  }

  @Override
  public Dish findById(long id) {
    // TypedQuery<Contact> query = this.entityManager.createNamedQuery("find_contact_by_id", Contact.class);
//    TypedQuery<Contact> query = findByQuery("find_contact_by_id",id);
//    return query.setParameter(1,id).getSingleResult();
    return entityManager.find(Dish.class, id);
  }

  @Override
  public List<Dish> findAll() {
    TypedQuery<Dish> query = this.entityManager.createNamedQuery("findAll_dish", Dish.class);
    return query.getResultList();

  }

  @Override
  public boolean delete(Dish contact) {
    Dish toRemove = entityManager.merge(entityManager.find(Dish.class, contact.getId()));
    entityManager.remove(toRemove);
    return false;
  }

  @Override
  public Dish save(Dish contact) {
    if (contact.getId() == 0) {
      entityManager.persist(contact);
    } else {
      entityManager.merge(contact);
    }
    return contact;
  }

  public List<Dish> findByQuery(String jpqlName, Object... params) {
    TypedQuery<Dish> namedQuery = entityManager.createNamedQuery(jpqlName, Dish.class);
    return namedQuery.setParameter(1, params[0]).getResultList();
  }
}

