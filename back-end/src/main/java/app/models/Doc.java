package app.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NamedQueries({
  @NamedQuery(name = "findDocByDateRes",
    query = "select d from Doc d " +
      "inner join Account a on a.username = d.username " +
      "where d.date = ?1 and a.location = ?2"),
})
public class Doc {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String doc_name;
  private String doc_type;

  @Lob
  private byte[] data;

  private String username;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate date = LocalDate.now();

  public Doc() {
  }

  public Doc(String docName, String docType, byte[] data) {
    super();
    this.doc_name = docName;
    this.doc_type = docType;
    this.data = data;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDoc_name() {
    return doc_name;
  }

  public void setDoc_name(String docName) {
    this.doc_name = docName;
  }

  public String getDoc_type() {
    return doc_type;
  }

  public void setDoc_type(String docType) {
    this.doc_type = docType;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String restaurant) {
    this.username = restaurant;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public LocalDate getDate() {
    return date;
  }
}
