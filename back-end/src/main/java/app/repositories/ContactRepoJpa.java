package app.repositories;

import app.models.Contact;
import app.models.Log;
import app.models.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Primary
@org.springframework.stereotype.Repository("CONTACT.JPA")
@Transactional
public class ContactRepoJpa implements Repository<Contact>{

  @Autowired
  EntityManager entityManager;

  public List<Contact> findAllContacts(String username) {
    TypedQuery<Contact> query = this.entityManager.createNamedQuery("findAll_contacts_with_username", Contact.class);
    return query.setParameter(1, username).getResultList();
  }

  @Override
  public Contact findById(long id) {
   // TypedQuery<Contact> query = this.entityManager.createNamedQuery("find_contact_by_id", Contact.class);
//    TypedQuery<Contact> query = findByQuery("find_contact_by_id",id);
//    return query.setParameter(1,id).getSingleResult();
    return entityManager.find(Contact.class, id);
  }

  @Override
  public List<Contact> findAll() {
    TypedQuery<Contact> query = this.entityManager.createNamedQuery("findAll_contacts", Contact.class);
    return query.getResultList();

  }

  @Override
  public boolean delete(Contact contact) {
    Contact toRemove = entityManager.merge(entityManager.find(Contact.class, contact.getId()));
    entityManager.remove(toRemove);
    return true;
  }

  @Override
  public Contact save(Contact contact) {
    if (contact.getId() == 0) {
      entityManager.persist(contact);
    } else {
      entityManager.merge(contact);
    }
    return contact;
  }

  public List<Contact> findByQuery(String jpqlName, Object... params) {
    TypedQuery<Contact> namedQuery = entityManager.createNamedQuery(jpqlName, Contact.class);
    return namedQuery.setParameter(1, params[0]).getResultList();
  }
}
