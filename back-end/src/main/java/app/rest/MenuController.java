package app.rest;

import app.exception.ResourceNotFoundException;
import app.models.Contact;
import app.models.Menu;
import app.repositories.ContactRepoJpa;
import app.repositories.DishRepoJpa;
import app.repositories.IngredientRepoJpa;
import app.repositories.MenuRepoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/menu")
public class MenuController {

  @Autowired
  private MenuRepoJpa repo;

  @GetMapping
  public List<Menu> findAll() {
    return repo.findAll();
  }

//  @GetMapping
//  public ResponseEntity findAll() {
//    List<Menu> list = repo.findByQuery("Select c from menu c");
//    return ResponseEntity.ok(list);
//  }

  @GetMapping(path = "/find/{id}")
  public Menu findContactById(@PathVariable long id) {
//    List<Contact> list = repo.findByQuery("find_contact_by_id", id);
//    return ResponseEntity.ok(list);
    Menu contact = repo.findById(id);

    if (contact == null) {
      throw new ResourceNotFoundException("id = " + id + " was not found");
    }
    return contact;
  }

  @PostMapping(path = "/{id}")
  public ResponseEntity<Menu> saveMenu(@RequestBody Menu newMenu, @PathVariable long id) {
    newMenu.setId(id);

    this.repo.save(newMenu);


    return ResponseEntity.ok().body(newMenu);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity deleteContact(@PathVariable long id) {
    Menu offer = repo.findById(id);

    if (offer == null) {
      throw new ResourceNotFoundException("id = " + id + " was not found");
    }

    this.repo.delete(offer);
    return ResponseEntity.ok("Contact with id: " + id + " has been deleted");
  }
}

