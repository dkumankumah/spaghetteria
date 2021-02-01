package app.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedNativeQueries({
  @NamedNativeQuery(name = "getAll_MessageContacts",
    query = "select  ac.username as username,  ac.name as name,  max(me.datetimesend) as datetimesend, ANY_VALUE(me.reciever) as reciever, ANY_VALUE(me.message) as message, ANY_VALUE(me.sender) as sender, ANY_VALUE(me.isread) as isread from message me right join account ac on ac.username = me.reciever or ac.username = me.sender where ac.username != ?1 and (me.sender = ?2 or me.reciever = ?3) group by username union select a.username as username,  a.name as name,   null as datetimesend, null as reciever, null as message, null as sender, null as isread from account a where a.username not in (select  ac.username as username from message me right join account ac on ac.username = me.reciever or ac.username = me.sender where ac.username != ?4 and (me.sender = ?5 or me.reciever = ?6)) and username != ?7 group by username order by datetimesend desc", resultClass = MessageContact.class),

  @NamedNativeQuery(
    name = "updateRead",
    query = "UPDATE message m set m.isread = 1 where m.sender = ?1 and m.reciever = ?2",resultClass = MessageContact.class)
})

@Entity
public class MessageContact {

  @Id
  private String username;
  private String name;
  private String sender;
  @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  private LocalDateTime datetimesend;
  private String reciever;
  private String message;
  @JsonProperty("isread")
  private Boolean isread;

  public MessageContact() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }


  public LocalDateTime getDatetimesend() {
    return datetimesend;
  }

  public void setDatetimesend(LocalDateTime datetimesend) {
    this.datetimesend = datetimesend;
  }

  public String getReciever() {
    return reciever;
  }

  public void setReciever(String reciever) {
    this.reciever = reciever;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Boolean getIsread() {
    return isread;
  }

  public void setIsread(Boolean isread) {
    this.isread = isread;
  }

  @Override
  public String toString() {
    return "MessageContact{" +
      "username='" + username + '\'' +
      ", name='" + name + '\'' +
      ", sender='" + sender + '\'' +
      ", datetimesend=" + datetimesend +
      ", reciever='" + reciever + '\'' +
      ", message='" + message + '\'' +
      ", isread=" + isread +
      '}';
  }
}
