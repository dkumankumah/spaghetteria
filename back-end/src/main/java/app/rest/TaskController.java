package app.rest;

import app.exception.PreConditionFailedException;
import app.exception.ResourceNotFoundException;
import app.models.Task;
import app.models.Test;
import app.repositories.TaskRepoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
public class TaskController {

  @Autowired
  TaskRepoJpa taskrepo;

  @GetMapping("/tasks")
  public List<Task> getAlltasks(@RequestParam(required = false) String username) {
    return taskrepo.FindByQuery("getAllTasks",username);
  }

  @GetMapping("/tasks/doneNotAdmin")
  public List<Task> getDoneForBranche(@RequestParam(required = false) String madeby) {
    return taskrepo.FindByQuery("find_Donetasks_NotAdmin",madeby);
  }

  @GetMapping("/tasks/allTaskBranche")
  public List<Task> getAlltasksBranche(@RequestParam(required = false) String restaurant) {
    return taskrepo.FindByQuery("getTasks_Branche",restaurant);
  }
  @GetMapping("/tasks/foradmin")
  @CrossOrigin
  public List<Task> getAllTforAdmin() { return taskrepo.findAllTasks(); }

  @GetMapping("/tasks/admintask")
  public List<Task> getAllAdmintasks(@RequestParam(required = false) String restaurant) {
    return taskrepo.findadminTask(restaurant);
  }

  @GetMapping("/tasks/admindone")
  public List<Task> getAllDonetasks() {
    return taskrepo.findDoneForall();
  }
  @GetMapping("/tasks/nonchecked")
  public List<Task> getNonChecked() {
    return taskrepo.GetNonChecked();
  }
  @GetMapping("/tasks/todo")
  public List<Task> getAllTodo() {
    return taskrepo.GetTodoTasks();
  }
//  @GetMapping("tasks/{username}")
//  public List<Task> getAllperoTask(@PathVariable("username") String username) {
//    return taskrepo.FindByQuery("get_personal_task",username);
//  }
 @GetMapping("/tasks/perso")
  public List<Task> test(@RequestParam(required = false) String username){
    //you can use test value here
    return taskrepo.FindByQuery("get_personal_task",username);
  }

  @DeleteMapping("/tasks/{id}")
  public ResponseEntity<Integer> deleteScooter(@PathVariable("id") int id){

    boolean isRemoved= taskrepo.delete(id);
    if (!isRemoved) {
      throw new ResourceNotFoundException("Unknown id = " + id);
    }

    return new ResponseEntity<>(id, HttpStatus.OK);
  }

  @PutMapping("/tasks/{id}")
  public ResponseEntity<Task> updateUser(@PathVariable("id") int id, @RequestBody Task taskDetails)
  {

    Task task = taskrepo.findById(id);
    if (task ==null){
      throw new PreConditionFailedException(" doesnt match path param=" + id);
    }else {
      task.setTitle(taskDetails.getTitle());
      task.setStatus(taskDetails.getStatus());
      task.setPriority(taskDetails.isPriority());
      task.setDescription(taskDetails.getDescription());
      task.setDate(taskDetails.getDate());
      task.setChecked(taskDetails.isChecked());
      task.setMadeBy(taskDetails.getMadeBy());
      task.setFile_name(taskDetails.getFile_name());
      Task updatedtask = taskrepo.save(task);
      return ResponseEntity.ok(updatedtask);
    }

  }
  @GetMapping("/tasks/{id}")
  public Task FindTask(@PathVariable("id") int id) {

      Task task = taskrepo.findById(id);
        if (task==null){
         //throw new ResourceNotFoundException("Unknown id ");
          throw new ResourceNotFoundException("Unknown id");
      }
        else return taskrepo.findById(id);
  }


  @PostMapping("/tasks")
  public ResponseEntity<Object> saveTask(@RequestBody Task task)
  {
      Task savedTask = taskrepo.save(task);
      return ResponseEntity.ok(savedTask);
//      URI location= ServletUriComponentsBuilder.fromCurrentRequest()
//        .path("/{id}").buildAndExpand(savedTask.getId()).toUri();
//      return ResponseEntity.created(location).build();
  }


  // NOTIFICATIONS
  @GetMapping("/tasks/deadline")
  public ResponseEntity<Object> findNotifications(@RequestParam(required = false) String date,
                                                  @RequestParam(required = false) String location) {

    Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");

    if (!date.equals("") && pattern.matcher(date).matches()) {
      List<Task> list = this.taskrepo.findOnDate(LocalDate.now().toString(), date, location);
      if(list != null) {
        return ResponseEntity.ok(list);
      } else {
        return null;
      }
    } else {
      throw new ResourceNotFoundException("Date not found or date pattern does not match");
    }
  }

  @GetMapping("/tasks/checkedByAdmin")
  public ResponseEntity<Object> findTasksAdmin() {

    List<Object[]> list = this.taskrepo.findByChecked();
    return ResponseEntity.ok(list);
  }
}
