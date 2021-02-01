package app.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Component
@Entity(name="dish")
@NamedQueries({
  @NamedQuery(name = "findAll_dish_with_username",
    query = "select c from dish c where c.username = ?1")
  ,
  @NamedQuery(name = "findAll_dish",
    query = "select c from dish c")
  ,
  @NamedQuery(name = "find_dish_by_id",
    query = "select c from dish c where c.id = ?1"),
})
public class Dish {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_dish", updatable = false, nullable = false)
  private long id;
  private String name;
  private String username;
  private String categorie;
  private int deleted;

  public int getDeleted() {
    return deleted;
  }

  public void setDeleted(int deleted) {
    this.deleted = deleted;
  }

  public String getCategorie() {
    return categorie;
  }

  public void setCategorie(String categorie) {
    this.categorie = categorie;
  }

//  @ManyToMany(cascade = CascadeType.REMOVE)
//  @JoinTable(
//    name = "dish_ingredient",
//    joinColumns = @JoinColumn(name = "id_dish"),
//    inverseJoinColumns = @JoinColumn(name = "id_ingredient")
//  )
//  private Set<Ingredient> ingredients;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
    name = "dish_ingredient",
    joinColumns = @JoinColumn(name = "id_dish"),
    inverseJoinColumns = @JoinColumn(name = "id_ingredient"
    )
  )
  private Set<Ingredient> dishs;

  public Set<Ingredient> getDishs() {
    return dishs;
  }

  public void setDishs(Set<Ingredient> dishs) {
    this.dishs = dishs;
  }
//  @ManyToMany(mappedBy = "dishs")
//  private Set<Menu> menu;

//  public Set<Menu> getMenu() {
//    return menu;
//  }
//
//  public void setMenu(Set<Menu> menu) {
//    this.menu = menu;
//  }

//  public Set<Ingredient> getIngredients() {
//    return ingredients;
//  }
//
//  public void setIngredients(Set<Ingredient> ingredients) {
//    this.ingredients = ingredients;
//  }

  public Dish(){}
  public Dish(String name, String categorie, Set<Ingredient> ingredient) {
    this.name = name;
    this.categorie = categorie;
    this.dishs = ingredient;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

