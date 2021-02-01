package app.repositories;

import app.models.Task;
import app.models.Taskstatus;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@org.springframework.stereotype.Repository
@Transactional
public class TaskRepoJpa {

  @PersistenceContext
  EntityManager entityManager;

  public List<Task> findAllTasks() {
    TypedQuery<Task> namedQuery = entityManager.createNamedQuery("find_All_tasks", Task.class);
    return namedQuery.getResultList();
  }

  public List<Task> findadminTask(String params) {
    TypedQuery<Task> namedQuery = entityManager.createNamedQuery("getAdminTask", Task.class).setParameter("params", params);
    return namedQuery.getResultList();
  }

  public boolean delete(Task entity) {
    return false;
  }

  public Task findById(int id) {
    return entityManager.find(Task.class,id);
  }

  public boolean delete(int id) {
    if(findById(id)!=null)
    {
      Task task = findById(id);
      entityManager.remove(task);
      return true;
    }
    else
      return false;
  }

  public Task save(Task task) {
    if(task == null){
      entityManager.persist(task);
      return task;
    }
    else return entityManager.merge(task);
  }

  public List<Task> findDoneForall() {
    TypedQuery<Task> namedQuery = entityManager.createNamedQuery("find_Donetasks", Task.class);
    return namedQuery.getResultList();
  }
  public List<Task> GetNonChecked() {
    TypedQuery<Task> namedQuery = entityManager.createNamedQuery("find_NotCheckedTasks", Task.class);
    return namedQuery.getResultList();
  }
  public List<Task> GetTodoTasks() {
    TypedQuery<Task> namedQuery = entityManager.createNamedQuery("Get_TODOtasks", Task.class);
    return namedQuery.getResultList();
  }

    public List<Task> FindByQuery(String jpqlName,Object params){
    return entityManager.createNamedQuery(jpqlName,Task.class).setParameter("params", params).getResultList();
  }



  // NOTIFICATIONS

  public List<Object[]> findByChecked() {
    TypedQuery<Object[]> namedQuery = entityManager.createNamedQuery("getChecked", Object[].class);
    namedQuery.setParameter(1, false);
    namedQuery.setParameter(2, Taskstatus.DONE);
    return namedQuery.getResultList();
  }

  public List<Task> findOnDate(String startDate, String endDate, String location) {
    TypedQuery<Task> namedQuery = entityManager.createNamedQuery("find_on_date_loc", Task.class);
    namedQuery.setParameter(1, startDate);
    namedQuery.setParameter(2, endDate);
    namedQuery.setParameter(3, location);
    namedQuery.setParameter(4, Taskstatus.TODO.toString());
    return namedQuery.getResultList();
  }
}
