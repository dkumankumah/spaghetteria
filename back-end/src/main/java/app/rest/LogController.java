package app.rest;

import app.exception.BadRequest;
import app.exception.NotFoundException;
import app.exception.PreConditionFailedException;
import app.exception.ResourceNotFoundException;
import app.models.Doc;
import app.models.Log;
import app.repositories.LogRepoJpa;
import app.service.DocStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/log")
public class LogController {

  @Autowired
  private LogRepoJpa repository;

  @Autowired
  private DocStorageService docStorageService;

  @GetMapping
  public ResponseEntity<Object> findAllLog(@RequestParam(required = false) String restaurant) {
    System.out.println(restaurant);
    List<Object[]> list = this.repository.findByQuery("find_all", restaurant);
    return ResponseEntity.ok(list);
  }

  @GetMapping("/view")
  public ResponseEntity<Object> find_all_view(@RequestParam(required = false) String date) {

//    Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");

//    if (!date.equals("") && pattern.matcher(date).matches()) {
    List<Object[]> list = this.repository.find_all_view();
    return ResponseEntity.ok(list);
//    } else {
//      throw new ResourceNotFoundException("Date not found or date pattern does not match");
//    }
  }

  @GetMapping("/{id}")
  public Log getLogById(@PathVariable long id) {

    Log log = repository.findById(id);

    if (log == null) {
      throw new ResourceNotFoundException("id = " + id + " was not found");
    }
    return log;
  }

  @PostMapping("/{restaurant}")
  public ResponseEntity<Object> createLog(@RequestBody Log log, @PathVariable String restaurant) {

    boolean checkIfExists = false;

    List<Log> duplicate;

    duplicate = this.repository.findByDate(log.getDate(), restaurant);

    if (duplicate.size() != 0) {
      throw new BadRequest("Log bestaat al voor deze datum en dit restaurant");
    }

    if (this.repository.findById(log.getId()) == null) {
      checkIfExists = true;
    }

    Log savedLog = repository.save(log);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
      .buildAndExpand(savedLog.getId()).toUri();

    if (checkIfExists) {
      return ResponseEntity.ok().body(savedLog);
    } else {
      return ResponseEntity.created(location).body(savedLog);
    }
  }

  @GetMapping("/downloadFile")
  public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam(required = false) String restaurant,
                                                        @RequestParam(required = false) String date) {

    List<Doc> fileToday;

    fileToday = this.repository.findByDateResDoc(LocalDate.parse(date), restaurant);

    if (fileToday.size() == 0) {
      throw new NotFoundException("No file found!");
    }

    if (docStorageService.getFile(fileToday.get(0).getId()).isPresent()) {
      Doc doc = docStorageService.getFile((int) fileToday.get(0).getId()).get();
      return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(doc.getDoc_type()))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + doc.getDoc_name() + "\"")
        .body(new ByteArrayResource(doc.getData()));
    }
    return null;
  }

  @GetMapping("/downloadFile/today")
  public ResponseEntity<ByteArrayResource> downloadFileToday(@RequestParam(required = false) String restaurant) {

    List<Doc> fileToday;
    LocalDate today = LocalDate.now();

    fileToday = this.repository.findByDateResDoc(today, restaurant);

    if (fileToday.size() == 0) {
      throw new NotFoundException("No file found!");
    }

    if (docStorageService.getFile(fileToday.get(0).getId()).isPresent()) {
      Doc doc = docStorageService.getFile((int) fileToday.get(0).getId()).get();
      return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(doc.getDoc_type()))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + doc.getDoc_name() + "\"")
        .body(new ByteArrayResource(doc.getData()));
    }
    return null;
  }

  @PostMapping("/uploadFiles")
  public void uploadMultipleFiles(@RequestParam("file") MultipartFile file, @RequestParam(required = false) String
    restaurant) {

    List<Doc> duplicate;

    LocalDate today = LocalDate.now();

    duplicate = this.repository.findByDateResDoc(today, restaurant);

    if (duplicate.size() != 0) {
      throw new BadRequest("There is already a doc for today");
    }

    docStorageService.saveFile(file);

  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateLog(@RequestBody Log newLog, @PathVariable long id) {

    if (newLog.getId() != 0) {
      if (newLog.getId() != id) {
        throw new PreConditionFailedException("Log = " + newLog.getId() + " does not match path parameter=" + id);
      }
    }

    Log oldLog = this.repository.findById(id);

    if (oldLog == null) {
      throw new ResourceNotFoundException("id = " + id + " was not found");
    }
    newLog.setId(id);
    this.repository.save(newLog);

    return ResponseEntity.ok().body(newLog);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Log> deleteLog(@PathVariable long id) {

    Log log = repository.findById(id);

    if (log == null) {
      throw new ResourceNotFoundException("id = " + id + " was not found");
    }

    this.repository.deleteById(id);
    return ResponseEntity.ok(log);
  }
}

