package app.rest;

import app.exception.ResourceNotFoundException;
import app.models.Contact;
import app.models.Dish;
import app.models.Ingredient;
import app.repositories.ContactRepoJpa;
import app.repositories.DishRepoJpa;
import app.repositories.IngredientRepoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/dish")
public class DishController {

  @Autowired
  private DishRepoJpa repo;
  private IngredientRepoJpa ingredientRepoJpa;

  @GetMapping
  public List<Dish> findAll() {
    return repo.findAll();
  }

  @GetMapping(path = "/find/{id}")
  public Dish findContactById(@PathVariable long id) {
//    List<Contact> list = repo.findByQuery("find_contact_by_id", id);
//    return ResponseEntity.ok(list);
    Dish contact = repo.findById(id);

    if (contact == null) {
      throw new ResourceNotFoundException("id = " + id + " was not found");
    }
    return contact;
  }

  @PostMapping(path = "/username/{username}/")
  public ResponseEntity findAll(@PathVariable String username) {
    List<Dish> list = repo.findByQuery("findAll_dish_with_username", username);
    return ResponseEntity.ok(list);
  }

  @PostMapping(path = "/{id}")
  public ResponseEntity saveContact(@RequestBody Dish newContact, @PathVariable long id) {
    newContact.setId(id);

    this.repo.save(newContact);

    return ResponseEntity.ok().body(newContact.getId());
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity deleteContact(@PathVariable long id) {
    Dish offer = repo.findById(id);

    if (offer == null) {
      throw new ResourceNotFoundException("id = " + id + " was not found");
    }

    this.repo.delete(offer);
    return ResponseEntity.ok().body(offer);
  }
}
