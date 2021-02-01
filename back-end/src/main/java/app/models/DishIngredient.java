package app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Set;

@Component
@Entity(name="dish_ingredient")
public class DishIngredient implements Serializable {

//  @GeneratedValue(strategy=GenerationType.IDENTITY)
//  @Id
//  @JoinColumn(name = "id_dish")
//  @OneToOne(cascade = CascadeType.ALL)
//  private Dish dish;
//
//
//  @JoinColumn(name = "id_ingredient")
//  @OneToOne(cascade = CascadeType.ALL)
//  private Ingredient ingredient;

  @Id
  @Column(name="id_dish", insertable=false, updatable=false)
  private long id_dish;
  @Id
  @Column(name="id_ingredient", insertable=false, updatable=false)
  private long id_ingredient;


public DishIngredient(){}
public DishIngredient(long id_dish, long id_ingredient) {
    this.id_dish = id_dish;
    this.id_ingredient = id_ingredient;
  }

//  public Dish getDish() {
//    return dish;
//  }
//
//  public void setDish(Dish dish) {
//    this.dish = dish;
//  }
//
//  public Ingredient getIngredient() {
//    return ingredient;
//  }
//
//  public void setIngredient(Ingredient ingredient) {
//    this.ingredient = ingredient;
//  }

  public long getId_dish() {
    return id_dish;
  }

  public void setId_dish(long id_dish) {
    this.id_dish = id_dish;
  }

  public long getId_ingredient() {
    return id_ingredient;
  }

  public void setId_ingredient(long id_ingredient) {
    this.id_ingredient = id_ingredient;
  }
}
