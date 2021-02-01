package app.rest;

import app.models.Test;
import app.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

  @Autowired
  private TestRepository repository;

  @GetMapping("/test")
  public List<Test> getAllOffers() {

    return repository.findAll();
  }

}
