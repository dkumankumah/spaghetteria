package app.repositories;

import app.models.Contact;
import app.models.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Primary
@org.springframework.stereotype.Repository("INGREDIENT.JPA")
@Transactional
public class IngredientRepoJpa implements Repository<Ingredient>{

  @Autowired
  EntityManager entityManager;

  public List<Ingredient> findAllContacts(String username) {
    TypedQuery<Ingredient> query = this.entityManager.createNamedQuery("findAll_ingredients", Ingredient.class);
    return query.getResultList();
  }

  public List<Ingredient> findAllIngredientsWithDish(long id ) {
    TypedQuery<Ingredient> query = this.entityManager.createNamedQuery("find_ingredients_by_id", Ingredient.class);
    return query.getResultList();
  }

  @Override
  public Ingredient findById(long id) {
    // TypedQuery<Contact> query = this.entityManager.createNamedQuery("find_contact_by_id", Contact.class);
//    TypedQuery<Contact> query = findByQuery("find_contact_by_id",id);
//    return query.setParameter(1,id).getSingleResult();
    return entityManager.find(Ingredient.class, id);
  }

  @Override
  public List<Ingredient> findAll() {
    TypedQuery<Ingredient> query = this.entityManager.createNamedQuery("findAll_ingredients", Ingredient.class);
    return query.getResultList();

  }

  @Override
  public boolean delete(Ingredient contact) {
    Ingredient toRemove = entityManager.merge(entityManager.find(Ingredient.class, contact.getId()));
    entityManager.remove(toRemove);
    return false;
  }

  @Override
  public Ingredient save(Ingredient contact) {
    if (contact.getId() == 0) {
      entityManager.persist(contact);
    } else {
      entityManager.merge(contact);
    }
    return contact;
  }

  public List<Ingredient> findByQuery(String jpqlName, Object... params) {
    TypedQuery<Ingredient> namedQuery = entityManager.createNamedQuery(jpqlName, Ingredient.class);
    return namedQuery.setParameter(1, params[0]).getResultList();
  }
}
