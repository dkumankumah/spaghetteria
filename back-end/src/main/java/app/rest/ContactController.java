package app.rest;

import app.exception.ResourceNotFoundException;
import app.models.Contact;
import app.repositories.ContactRepoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/contact")
public class ContactController {

  @Autowired
  private ContactRepoJpa repo;

  @GetMapping
  public List<Contact> findAll(){
    return repo.findAll();
  }

  @GetMapping(path="/find/{id}")
  public Contact findContactById(@PathVariable  long id){
//    List<Contact> list = repo.findByQuery("find_contact_by_id", id);
//    return ResponseEntity.ok(list);
    Contact contact = repo.findById(id);

    if (contact == null) {
      throw new ResourceNotFoundException("id = " + id + " was not found");
    }
    return contact;
  }

  @PostMapping(path="/username/{username}/")
  public ResponseEntity findAll(@PathVariable String username) {
    List<Contact> list = repo.findByQuery("findAll_contacts_with_username", username);
    return ResponseEntity.ok(list);
  }

  @PostMapping(path = "/{id}")
  public ResponseEntity saveContact( @RequestBody Contact newContact, @PathVariable long id){
    newContact.setId(id);

    this.repo.save(newContact);


    return ResponseEntity.ok().body(newContact);
  }

  @DeleteMapping(path= "/{id}")
  public ResponseEntity deleteContact(@PathVariable long id){
    Contact offer = repo.findById(id);

    if (offer == null) {
      throw new ResourceNotFoundException("id = " + id + " was not found");
    }

    this.repo.delete(offer);
    return ResponseEntity.ok().body(offer);
  }
}
