package app.repositories;

import app.models.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@org.springframework.stereotype.Repository
@Transactional
public class TestRepository implements Repository<Test> {

  @Autowired
  EntityManager entityManager;

  @Override
  public List<Test> findAll() {
    TypedQuery<Test> query =
      this.entityManager.createQuery(
        "select t from Test t", Test.class);

    return query.getResultList();
  }

  @Override
  public boolean delete(Test entity) {
    return false;
  }

  @Override
  public Test findById(long id) {
    return null;
  }

  @Override
  public Test save(Test entity) {
    return null;
  }
}
