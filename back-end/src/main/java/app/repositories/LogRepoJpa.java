package app.repositories;

import app.models.Doc;
import app.models.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Primary
@Transactional
@Repository("LOG.JPA")
public class LogRepoJpa {

  @Autowired
  @PersistenceContext
  EntityManager entityManager;

  public Log save(Log log) {

    if (log.getId() == 0) {
      entityManager.persist(log);
    } else {
      entityManager.merge(log);
    }
    return log;
  }

  public boolean deleteById(long id) {
    Log toRemove = entityManager.merge(entityManager.find(Log.class, id));
    entityManager.remove(toRemove);
    return true;
  }

  public List<Object[]> find_all_view() {
    TypedQuery<Object[]> namedQuery = entityManager.createNamedQuery("find_all_view", Object[].class);
    namedQuery.setParameter(1, false);
    return namedQuery.getResultList();
  }

  public List<Log> findByDate(LocalDate date, String location) {
    TypedQuery<Log> namedQuery = entityManager.createNamedQuery("findLogDuplDate", Log.class);
    namedQuery.setParameter(1, date);
    namedQuery.setParameter(2, location);
    return namedQuery.getResultList();
  }

  public List<Object[]> findByQuery(String jpqlName, Object... params) {

    TypedQuery<Object[]> namedQuery = entityManager.createNamedQuery(jpqlName, Object[].class);
    if(!params[0].equals("-1")) {
      namedQuery.setParameter(1, params[0]);
    }
    return namedQuery.getResultList();
  }

  public List<Doc> findByDateResDoc(LocalDate date, String restaurant) {
    TypedQuery<Doc> namedQuery = entityManager.createNamedQuery("findDocByDateRes", Doc.class);
    namedQuery.setParameter(1, date);
    namedQuery.setParameter(2, restaurant);
    return namedQuery.getResultList();
  }

  public boolean delete(Log log) {
    return false;
  }

  public Log findById(long id) {
    return entityManager.find(Log.class, id);
  }

}
