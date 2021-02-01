package app.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedNativeQueries({
  @NamedNativeQuery(
    name = "getMessages",
    query = "select * from message m where (m.reciever = ?1 && m.sender = ?2) or (m.reciever = ?3 && m.sender = ?4);",resultClass = Message.class),
  @NamedNativeQuery(
    name = "getMessagesPinned",
    query = "select * from message m where ((m.reciever = ?1 && m.sender = ?2) or (m.reciever = ?3 && m.sender = ?4)) and m.pinned = 1;",resultClass = Message.class)
})
// NOTIFICATIONS
@NamedQueries({
  @NamedQuery(name = "find_unread",
    query = "select m from Message m " +
      "where m.isread = ?1 and m.reciever like ?2")
})
@Entity
@Component
@Table(name = "message")
public class Message {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private int idmessage;
  private String reciever;
  private String message;
  private String sender;
  @Column(name = "isread")
  private boolean isread;
  @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  @Column(name = "datetimesend")
  private LocalDateTime datetimesend;
  private boolean pinned;


  public int getIdmessage() {
    return idmessage;
  }

  public void setIdmessage(int idmessage) {
    this.idmessage = idmessage;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getReciever() {
    return reciever;
  }

  public void setReciever(String reciever) {
    this.reciever = reciever;
  }

  public LocalDateTime getDatetimesend() {
    return datetimesend;
  }

  public void setDatetimesend(LocalDateTime datetimesend) {
    this.datetimesend = datetimesend;
  }

  public boolean isIsread() {
    return isread;
  }

  public void setIsread(boolean isread) {
    this.isread = isread;
  }

  public boolean isPinned() {
    return pinned;
  }

  public void setPinned(boolean pinned) {
    this.pinned = pinned;
  }
}


