package app.repositories;
import java.util.List;

public interface Repository<E> {

  public List<E> findAll();

  public boolean delete(E entity);

  E findById(long id);

  E save(E entity);

}
