package app.models;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Component
@Entity(name="contact")
@NamedQueries({
  @NamedQuery(name = "findAll_contacts_with_username",
    query = "select c from contact c where c.username = ?1")
  ,
  @NamedQuery(name = "findAll_contacts",
    query = "select c from contact c")
  ,
  @NamedQuery(name = "find_contact_by_id",
    query = "select c from contact c where c.idContacts = ?1"),
})
public class Contact {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idContacts", updatable = false, nullable = false)
  private long idContacts;

  private String  phoneNumber; //phone number of the contact
  private String email;  //email of the contact
  private String adres; //adres of the contact
  private String task; //what does this contact do? delivers products? plumber?
  private String name; //name of the contact
  private String service; //what kind of contact is it? external or a supplier?
  private String username;

  public Contact(){}
  public Contact(String phoneNumber, String email, String adres, String task, String name, String service) {
//    Map<Integer,String> product
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.adres = adres;
    this.task = task;
    this.name = name;
    this.service = service;
    //this.product = product;
  }


  public long getId() {
    return idContacts;
  }

  public void setId(long id) {
    this.idContacts = id;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAdres() {
    return adres;
  }

  public void setAdres(String adres) {
    this.adres = adres;
  }

  public String getTask() {
    return task;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setTask(String task) {
    this.task = task;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getService() {
    return service;
  }

  public void setService(String service) {
    this.service = service;
  }

//  public Map<Integer, String> getProduct() {
//    return product;
//  }

//  public void setProduct(Map<Integer, String> product) {
//    this.product = product;
//  }
  public enum Service {
    External,
    Supplier
  }
}

