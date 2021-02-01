package app.rest;

import app.exception.ResourceNotFoundException;
import app.exception.UnauthorizedException;
import app.models.Account;
import app.repositories.AccountRepository;
import app.security.PasswordEncoder;
import app.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class AccountController {
  @Autowired
  private AccountRepository repository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @GetMapping("/Account")
  public List<Account> getAllAccount() {
    return repository.findAll();
  }

  @GetMapping("Account/user")
  public Account getAccount(@RequestParam(required = false)  String username) {
    Account account = repository.findByUser(username);
    if (account == null) {
      throw new ResourceNotFoundException("Unknown username = " + username);
    } else return account;
  }

  @PutMapping("Account/user")
  public ResponseEntity<Account> updateUser(@RequestParam(required = false)  String username,
                                            @RequestBody Account accountNew)
  {

    Account account = repository.findByUser(username);
    if (account == null) {
      throw new ResourceNotFoundException("Unknown username = " + username);
    }else {
      account.setName(accountNew.getName());
      account.setPassword(passwordEncoder.encode(accountNew.getPassword()));
      account.setLocation(accountNew.getLocation());
      account.setPhoneNumber(accountNew.getPhoneNumber());
      account.setAdmin(accountNew.isAdmin());
      account.setActive(accountNew.isActive());
      Account updatedAccount = repository.save(account);
      return ResponseEntity.ok(updatedAccount);
    }

  }

  @GetMapping("/Account/restaurant")
  public List<String> getAllRestaurant() {
    return repository.GetRestaurant();
  }

//  @DeleteMapping("Account/{username}")
//  @CrossOrigin
//  public ResponseEntity<String> deleteAccount(@PathVariable String username){
//
//    Account account = repository.findByUser(username);
//    boolean isRemoved = repository.delete(account);
//    if (!isRemoved) {
//      throw new ResourceNotFoundException("Unknown username = " + username);
//    }
//    return new ResponseEntity<>(username, HttpStatus.OK);
//  }

  @PostMapping("/Account")
  public ResponseEntity<Account> saveAccount(@RequestBody Account account)
  {
    account.setPassword(passwordEncoder.encode(account.getPassword()));
    Account savedAccount = repository.save(account);

    return ResponseEntity.ok(savedAccount);

  }

  @PostMapping("/Account/login")
  @JsonView(Views.external.class)
  public ResponseEntity<Account> login(@RequestBody Map<String, String> json) throws UnauthorizedException {
    Account account = new Account();
    account.setName(json.get("username"));
    account.setPassword(passwordEncoder.encode(json.get("password")));
    Account account1 = repository.login(account);
    return ResponseEntity.ok().body(account1);
  }

}


