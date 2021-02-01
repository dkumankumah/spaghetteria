package app.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
import java.time.format.DateTimeFormatter;

@Entity
@Table (name="task")
@NamedQueries({
  @NamedQuery(name = "find_All_tasks",
    query = "select t from Task t Where t.personalTask=false and t.checked=false"),
  @NamedQuery(name = "find_Donetasks",
    query = "select t from Task t WHERE t.status='DONE' and t.checked=true and t.personalTask=false"),
  @NamedQuery(name="find_Donetasks_NotAdmin",
    query="select t from Task t left join Account a on t.username=a.username where t.status='DONE' and t.madeby= :params"),
  @NamedQuery(name="getAllTasks",
    query = "select t from Task t left join Account a on t.username=a.username where a.admin=true or t.username= :params"),
  @NamedQuery(name="getTasks_Branche",
    query = "select t from Task t left join Account a on t.username=a.username where t.restaurant= :params"),
  @NamedQuery(name="get_personal_task",
    query = "SELECT t FROM Task t where t.username= :params"),
  @NamedQuery(name = "getAdminTask",
    query = "select t from Task t left join Account a on t.username=a.username where a.admin=true and t.restaurant= :params"),
  @NamedQuery(name = "find_NotCheckedTasks",
    query = "select t from Task t WHERE t.checked=false and t.status='DONE' and t.personalTask=false"),
  @NamedQuery(name = "Get_TODOtasks",
    query = "select t from Task t WHERE t.status='TODO' and t.personalTask=false"),

// NOTIFICATIONS
@NamedQuery(name = "getChecked",
  query = "select t, a.location from Task t " + "inner join Account a " +
    "on a.username = t.username " +
    "where t.checked = ?1 and t.status = ?2 and t.personalTask=false"),
  })

@NamedNativeQueries({
  @NamedNativeQuery(
    name = "find_on_date_loc",
    query = "SELECT * from task t inner join account a " +
      "on a.username = t.username " +
      "where t.restaurant like ?3 and t.status = ?4 and t.date between ?1 and ?2",
    resultClass=Task.class
  )
})

public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 private String title;
 private String description;
 @Enumerated(EnumType.STRING)
 private Taskstatus status ;
 private boolean priority;

 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
 private LocalDate date;
 private boolean checked;
 private String username;
 private String madeby;
 private boolean personalTask;
 private String restaurant;
 private String file_name;

  public Task(int id, String title, String description, Taskstatus status, boolean priority, Date date, boolean checked, String username, String madeby , boolean personalTask, String restaurant , String file_name) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
    this.priority = priority;
    this.date = date.toLocalDate();
    this.checked=checked;
    this.username=username;
    this.madeby=madeby;
    this.personalTask = personalTask;
    this.restaurant = restaurant;
    this.file_name= file_name;
  }
  public Task() {
  }
  public boolean isPersonalTask() {
    return personalTask;
  }
  public void setPersonalTask(boolean personalTask) {
    this.personalTask = personalTask;
  }
  public String getRestaurant() {
    return restaurant;
  }
  public void setRestaurant(String restaurant) {
    this.restaurant = restaurant;
  }
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getMadeBy() {
    return madeby;
  }

  public void setMadeBy(String madeBy) {
    this.madeby = madeBy;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Taskstatus getStatus() {
    return status;
  }

  public void setStatus(Taskstatus status) {
    this.status = status;
  }

  public boolean isPriority() {
    return priority;
  }

  public void setPriority(boolean priority) {
    this.priority = priority;
  }

  public boolean isChecked() {
    return checked;
  }

  public void setChecked(boolean checked) {
    this.checked = checked;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getFile_name() {
    return file_name;
  }

  public void setFile_name(String file_name) {
    this.file_name = file_name;
  }
}

