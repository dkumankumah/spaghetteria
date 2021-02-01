package app.rest;

import app.exception.ResourceNotFoundException;
import app.models.Contact;
import app.models.Menu;
import app.models.MenuDish;
import app.repositories.MenuDishRepoJpa;
import app.repositories.MenuRepoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/menuDish")
public class MenuDishController {
  @Autowired
  private MenuDishRepoJpa repo;

  @GetMapping(path = "/{id}")
  public List<MenuDish> findAllMenus(@PathVariable long id) {
    return this.repo.findAllMenus(id);
  }

  @DeleteMapping(path= "/delete/{id}")
  public ResponseEntity deleteContact(@PathVariable long id){
    this.repo.delete(id);
    return ResponseEntity.ok().body(id);
  }
}
