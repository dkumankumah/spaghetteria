package app.rest;

import app.exception.ResourceNotFoundException;
import app.models.Contact;
import app.models.Drink;
import app.repositories.ContactRepoJpa;
import app.repositories.DrinkRepoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/drink")
public class DrinkController {

  @Autowired
  private DrinkRepoJpa repo;

  @GetMapping
  public List<Drink> findAll() {
    return repo.findAll();
  }

  @GetMapping(path = "/find/{id}")
  public Drink findDrinkById(@PathVariable long id) {
//    List<Contact> list = repo.findByQuery("find_contact_by_id", id);
//    return ResponseEntity.ok(list);
    Drink contact = repo.findById(id);

    if (contact == null) {
      throw new ResourceNotFoundException("id = " + id + " was not found");
    }
    return contact;
  }

  @PostMapping(path = "/username/{username}/")
  public ResponseEntity findAll(@PathVariable String username) {
    List<Drink> list = repo.findByQuery("findAll_drinks_with_username", username);
    return ResponseEntity.ok(list);
  }

  @PostMapping(path = "/{id}")
  public ResponseEntity saveContact(@RequestBody Drink newContact, @PathVariable long id) {
    newContact.setIdDrink(id);

    this.repo.save(newContact);


    return ResponseEntity.ok().body(newContact);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity deleteContact(@PathVariable long id) {
    Drink offer = repo.findById(id);

    if (offer == null) {
      throw new ResourceNotFoundException("id = " + id + " was not found");
    }

    this.repo.delete(offer);
    return ResponseEntity.ok().body(offer);
  }
}
