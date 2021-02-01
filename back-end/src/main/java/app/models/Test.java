package app.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
public class Test {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private int id;

  private String naam;

  public Test() {
  }

  public Test(int id, String naam) {
    this.id = id;
    this.naam = naam;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNaam() {
    return naam;
  }

  public void setNaam(String naam) {
    this.naam = naam;
  }
}
