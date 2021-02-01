package app.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity(name="drink")
@NamedQueries({
  @NamedQuery(name = "findAll_drinks_with_username",
    query = "select c from drink c where c.username = ?1")
  ,
  @NamedQuery(name = "findAll_drinks",
    query = "select c from drink c")
  ,
  @NamedQuery(name = "find_drinks_by_id",
    query = "select c from drink c where c.id = ?1"),
})
public class Drink {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idDrink", updatable = false, nullable = false)
  private long id;
  private String name;
  private String username;

  public Drink(){}
  public Drink(String name) {
    this.name = name;
  }

  public long getIdDrink() {
    return id;
  }

  public void setIdDrink(long idDrink) {
    this.id = idDrink;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}



