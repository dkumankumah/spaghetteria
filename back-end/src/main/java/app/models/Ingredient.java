package app.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Component
@Entity(name="ingredient")
@NamedQueries({
  @NamedQuery(name = "findAll_ingredients",
//    query = "select c from ingredient c " +
//      "join c.id dish d on c.id = d.id where d.username = ?1")
//    query = "SELECT i FROM ingredient i where dish.username = ?1")
//  query = "SELECT d  FROM dish d where d.username = ?1")
query = "SELECT distinct i FROM ingredient i join dish_ingredient d on i.id_ingredient = d.id_ingredient join dish z on z.id = d.id_dish where z.username = ?1")

  ,
  @NamedQuery(name = "find_ingredients_by_id",
    query = "SELECT distinct i FROM ingredient i join dish_ingredient d on i.id_ingredient = d.id_ingredient join dish z on z.id = d.id_dish where  z.id = ?1")
})
public class Ingredient {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_ingredient", updatable = false, nullable = false)
  private long id_ingredient;

  private String nameNL;

  private String nameIT;

//  @ManyToMany(mappedBy = "ingredients")
//  private Set<Dish> dishes;

//  public Set<Dish> getDishes() {
//    return dishes;
//  }
//
//  public void setDishes(Set<Dish> dishes) {
//    this.dishes = dishes;
//  }

  public Ingredient(){}
  public Ingredient(String nameNL, String nameIT) {
    this.nameNL = nameNL;
    this.nameIT = nameIT;
  }

  public long getId() {
    return id_ingredient;
  }

  public void setId(long id) {
    this.id_ingredient = id;
  }

  public String getNameNL() {
    return nameNL;
  }

  public void setNameNL(String nameNL) {
    this.nameNL = nameNL;
  }

  public String getNameIT() {
    return nameIT;
  }

  public void setNameIT(String nameIT) {
    this.nameIT = nameIT;
  }
}
