package app.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Component
@Entity(name="menu")
@NamedQueries({
  @NamedQuery(name = "findAll_menu",
    query = "select c from menu c")
  ,
//  @NamedQuery(name = "findAll_menu_with_username",
//    query = "select c from menu c where c.username = ?1")
//  ,
  @NamedQuery(name = "find_menu_by_id",
    query = "select c from menu c where c.id = ?1")
})
public class Menu {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_menu", updatable = false, nullable = false)
  private long id;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
    name = "menu_dish",
    joinColumns = @JoinColumn(name = "id_menu"),
    inverseJoinColumns = @JoinColumn(name = "id_dish"
    )
  )
  private Set<Dish> dishs;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Set<Dish> getDishs() {
    return dishs;
  }

  public void setDishs(Set<Dish> dishs) {
    this.dishs = dishs;
  }

  public Menu(){}
  public Menu(Set<Dish> dishs) {
    this.dishs = dishs;
  }
}

