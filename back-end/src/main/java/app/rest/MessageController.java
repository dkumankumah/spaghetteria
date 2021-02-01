package app.rest;

import app.models.Message;
import app.models.MessageContact;
import app.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MessageController {

  @Autowired
  private MessageRepository messageRepository;

  @PostMapping("/Message/contact")
  @CrossOrigin
  public ResponseEntity<List<MessageContact>> messageContact(@RequestBody Map<String, String> Json) {

    List<MessageContact> messageContacts = new ArrayList<>();
    messageContacts = messageRepository.getMessageContacts(Json.get("username"));




    return ResponseEntity.ok().body(messageContacts);
  }

  @PutMapping("/Message/read")
  @CrossOrigin
  public ResponseEntity<Integer> updateRead(@RequestBody Map<String, String> Json){



    return ResponseEntity.ok().body(messageRepository.updateRead(Json.get("sender"), Json.get("reciever")));
  }

  @PostMapping("/Message/send")
  @CrossOrigin
  public ResponseEntity<Message> saveMessage(@RequestBody Message message){

    return ResponseEntity.ok().body(messageRepository.save(message));
  }

  @PostMapping("/Message/messages")
  @CrossOrigin
  public ResponseEntity<List<Message>> getMessages(@RequestBody Map<String, String> Json){

    return ResponseEntity.ok().body(messageRepository.getMessages(Json.get("user1"), Json.get("user2")));
  }



  // NOTIFICATIONS
  @GetMapping("/message/notifications")
  public ResponseEntity<Object> findNotifications(@RequestParam(required = false) String location) {
    System.out.println(location);
    List<Message> list = this.messageRepository.findNotifications(location);
    return ResponseEntity.ok(list);
    }
  @PostMapping("/Message/messages/pinned")
  @CrossOrigin
  public ResponseEntity<List<Message>> getMessagesPinned(@RequestBody Map<String, String> Json){

    return ResponseEntity.ok().body(messageRepository.getMessagesPinned(Json.get("user1"), Json.get("user2")));
  }
}
