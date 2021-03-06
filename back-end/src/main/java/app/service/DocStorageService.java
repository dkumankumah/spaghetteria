package app.service;

import app.models.Doc;
import app.repositories.DocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class DocStorageService {
  @Autowired
  private DocRepository docRepository;

  public Doc saveFile(MultipartFile file) {
    String docname = file.getOriginalFilename();

    try {
      Doc doc = new Doc(docname, file.getContentType(), file.getBytes());
      doc.setUsername(docname);
      doc.setDoc_type("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
      return docRepository.save(doc);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public Optional<Doc> getFile(Integer fileId) {

    return docRepository.findById(fileId);
  }

  public List<Doc> getFiles() {
    return docRepository.findAll();
  }
}
