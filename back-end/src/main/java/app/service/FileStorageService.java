package app.service;

import app.models.Files;
import app.repositories.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageService {

  @Autowired
  private FileDBRepository fileDBRepository;

  public Files store(MultipartFile file) throws IOException{
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    Files files = new Files(fileName, file.getContentType(), file.getBytes());
    return fileDBRepository.save(files);
  }

  public Files getFile(String id) {
    return fileDBRepository.findById(id).get();
  }

  public Stream<Files> getAllFiles() {
    return fileDBRepository.findAll().stream();
  }
}
