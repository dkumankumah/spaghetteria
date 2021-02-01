package app.repositories;

import app.models.DishIngredient;
import app.models.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Primary
@org.springframework.stereotype.Repository("DISHINGREDIENT.JPA")
@Transactional
public class DishIngredientRepoJpa implements Repository<DishIngredient> {

  @Autowired
  EntityManager entityManager;

  @Override
  public DishIngredient save(DishIngredient dish) {
    entityManager.persist(dish);
    return dish;
  }

  @Override
  public List<DishIngredient> findAll() {
    return null;
  }

  @Override
  public boolean delete(DishIngredient entity) {
    return false;
  }

  @Override
  public DishIngredient findById(long id) {
    return null;
  }


}
