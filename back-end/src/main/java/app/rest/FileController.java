package app.rest;

import app.message.ResponseFile;
import app.message.ResponseMessage;
import app.models.Files;
import app.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class FileController {

  @Autowired
  FileStorageService storageService;

  @PostMapping("file/upload")
  @CrossOrigin
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    try {
      storageService.store(file);

      message = "Uploaded the file successfully: " + file.getOriginalFilename() + " test :" + file.getName();

      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping("/files")
  @CrossOrigin
  public ResponseEntity<List<ResponseFile>> getListFiles() {
    List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
      String fileDownloadUri = ServletUriComponentsBuilder
        .fromCurrentContextPath()
        .path("/files")
        .path(dbFile.getId())
        .toUriString();

      return new ResponseFile(
        dbFile.getFileName(),
        fileDownloadUri,
        dbFile.getFileType(),
        dbFile.getData().length);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(files);
  }

  @GetMapping("/files/{naam}")
  public ResponseEntity<byte[]> getFile(@PathVariable String naam) {
    Files fileDB = storageService.getAllFiles()
      .filter(files -> files.getFileName().equals(naam)).findFirst().get();

    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getFileName() + "\"")
      .body(fileDB.getData());
  }

//  @DeleteMapping("/files/{naam}")
//  public ResponseEntity<byte[]> getFile(@PathVariable String naam) {
//    Files fileDB = storageService.getAllFiles()
//      .filter(files -> files.getFileName().equals(naam)).findFirst().get();
//
//    storageService.
//
//    return ResponseEntity.ok()
//      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getFileName() + "\"")
//      .body(fileDB.getData());
//  }

}
