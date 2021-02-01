package app.rest;

import app.models.DishIngredient;
import app.models.Ingredient;
import app.repositories.DishIngredientRepoJpa;
import app.repositories.IngredientRepoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/dishIngredient")
public class DishIngredientController {

  @Autowired
  private DishIngredientRepoJpa repo;

  @PostMapping
  public ResponseEntity saveDishIngredient(@RequestBody DishIngredient dish) {
    this.repo.save(dish);
    return ResponseEntity.ok().body(dish);
  }
}
