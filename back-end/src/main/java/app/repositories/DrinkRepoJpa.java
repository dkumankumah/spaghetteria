package app.repositories;

import app.models.Contact;
import app.models.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Primary
@org.springframework.stereotype.Repository("DRINK.JPA")
@Transactional
public class DrinkRepoJpa implements Repository<Drink> {

  @Autowired
  EntityManager entityManager;

  public List<Drink> findAllDrinks(String username) {
    TypedQuery<Drink> query = this.entityManager.createNamedQuery("findAll_drinks_with_username", Drink.class);
    return query.setParameter(1, username).getResultList();
  }

  @Override
  public Drink findById(long id) {
    // TypedQuery<Contact> query = this.entityManager.createNamedQuery("find_contact_by_id", Contact.class);
//    TypedQuery<Contact> query = findByQuery("find_contact_by_id",id);
//    return query.setParameter(1,id).getSingleResult();
    return entityManager.find(Drink.class, id);
  }

  @Override
  public List<Drink> findAll() {
    TypedQuery<Drink> query = this.entityManager.createNamedQuery("findAll_drinks", Drink.class);
    return query.getResultList();

  }

  @Override
  public boolean delete(Drink contact) {
    Drink toRemove = entityManager.merge(entityManager.find(Drink.class, contact.getIdDrink()));
    entityManager.remove(toRemove);
    return false;
  }

  @Override
  public Drink save(Drink contact) {
    if (contact.getIdDrink() == 0) {
      entityManager.persist(contact);
    } else {
      entityManager.merge(contact);
    }
    return contact;
  }

  public List<Drink> findByQuery(String jpqlName, Object... params) {
    TypedQuery<Drink> namedQuery = entityManager.createNamedQuery(jpqlName, Drink.class);
    return namedQuery.setParameter(1, params[0]).getResultList();
  }
}
