package app.repositories;

import app.models.Account;
import javax.persistence.EntityManager;
import java.util.List;

@org.springframework.stereotype.Repository
public class LoginRepository implements Repository<Account> {

  private EntityManager entityManager;

  @Override
  public List<Account> findAll() {
    return null;
  }

  @Override
  public boolean delete(Account entity) {
    return false;
  }

  @Override
  public Account findById(long id) {
    return null;
  }

  @Override
  public Account save(Account entity) {
    return null;
  }
}
