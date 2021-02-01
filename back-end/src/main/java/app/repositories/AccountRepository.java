package app.repositories;

import app.exception.UnauthorizedException;
import app.models.Account;
import app.models.Task;
import app.models.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
@Transactional
public class AccountRepository {

  @Autowired
  @PersistenceContext
  EntityManager entityManager;

  public List<Account> findAll() {
    TypedQuery<Account> query =
      this.entityManager.createQuery(
        "select a from Account a", Account.class);

    return query.getResultList();
  }

  public boolean delete(Account entity) {
//    if(findByUser(entity.getUsername())!= null){
//      entityManager.remove(entity);
//      return true;
//    }
//    else return false;
    return false;
  }

  public Account findById(long id) {
    return null;
  }

  public Account save(Account entity) {
    if(entity == null){
      entityManager.persist(entity);
      return entity;
    }
    else return entityManager.merge(entity);
  }
  public List<String> GetRestaurant() {
    return entityManager.createNamedQuery("GetRestaurant").getResultList();
  }

  public Account findByUser(String username){
    return entityManager.find(Account.class,username);
  }

  public Account login(Account entity) throws UnauthorizedException {
    TypedQuery<Account> query = entityManager.createNamedQuery("Login", Account.class);
    List<Account> account = new ArrayList<>();

   account = query.setParameter(1, entity.getName()).setParameter(2, entity.getPassword()).getResultList();

    if (account.size() == 0) {
      return new Account();
    }

    return account.get(0);
  }
}
