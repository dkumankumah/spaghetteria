package app.rest;

import app.exception.ResourceNotFoundException;
import app.models.Contact;
import app.models.Ingredient;
import app.repositories.ContactRepoJpa;
import app.repositories.IngredientRepoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/ingredient")
public class IngredientController {

  @Autowired
  private IngredientRepoJpa repo;

  @GetMapping
  public List<Ingredient> findAll() {
    return repo.findAll();
  }

  @GetMapping(path = "/find/{id}")
  public Ingredient findContactById(@PathVariable long id) {
//    List<Contact> list = repo.findByQuery("find_contact_by_id", id);
//    return ResponseEntity.ok(list);
    Ingredient contact = repo.findById(id);

    if (contact == null) {
      throw new ResourceNotFoundException("id = " + id + " was not found");
    }
    return contact;
  }
  @GetMapping(path = "/findAll/{id}")
  public List<Ingredient> findAllingredientsByDish(@PathVariable long id) {
//    List<Contact> list = repo.findByQuery("find_contact_by_id", id);
//    return ResponseEntity.ok(list);
    List<Ingredient> list = repo.findByQuery("find_ingredients_by_id", id);
//    List<Ingredient> contact = repo.findAllIngredientsWithDish(id);

    return list;
  }

  @PostMapping(path = "/username/{username}/")
  public ResponseEntity<List<Ingredient>> findAll(@PathVariable String username) {
    List<Ingredient> list = repo.findByQuery("findAll_ingredients", username);
    return ResponseEntity.ok(list);
  }

  @GetMapping(path = "/username")
  public ResponseEntity findAllusername() {
    List<Ingredient> list = repo.findByQuery("findAll_ingredients");
    return ResponseEntity.ok(list);
  }

  @PostMapping(path = "/{id}")
  public ResponseEntity saveContact(@RequestBody Ingredient newContact, @PathVariable long id) {
    newContact.setId(id);

    Ingredient i = this.repo.save(newContact);


    return ResponseEntity.ok().body(i);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Object> deleteContact(@PathVariable long id) {
    Ingredient offer = repo.findById(id);

    if (offer == null) {
      throw new ResourceNotFoundException("id = " + id + " was not found");
    }

    this.repo.delete(offer);
    return ResponseEntity.ok("Contact with id: " + id + " has been deleted");
  }
}
