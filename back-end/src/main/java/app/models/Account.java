package app.models;

import org.springframework.stereotype.Component;
import app.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Component
@Entity
@NamedQueries({
@NamedQuery(name = "Login",
  query = "SELECT a FROM Account a where a.username = ?1 and a.password = ?2"),
@NamedQuery(name = "GetRestaurant",
    query = "SELECT distinct location FROM Account"),
})

@Table(name = "Account")
public class Account {

  @Id
  @Column(name = "username", unique = true, nullable = false)
  @JsonView(Views.external.class)
  private String username;

  private String password;
  @Column(name = "phonenumber")
  @JsonView(Views.external.class)
  private String phoneNumber;
  @JsonView(Views.external.class)
  private String name;
  @JsonView(Views.external.class)
  private String location;
  @JsonView(Views.external.class)
  private boolean admin;
  @JsonView(Views.external.class)
  private boolean active;

  public Account() {
  }

  public Account(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public Account(String username, String password, String phoneNumber, String name, String location, boolean active) {
    this.username = username;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.name = name;
    this.location = location;
    this.active = active;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public String toString() {
    return "Account{" +
      "username='" + username + '\'' +
      ", password='" + password + '\'' +
      ", phoneNumber='" + phoneNumber + '\'' +
      ", name='" + name + '\'' +
      ", location='" + location + '\'' +
      ", admin=" + admin +
      '}';
  }
}
