package app.repositories;

import app.models.Message;
import app.models.MessageContact;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@org.springframework.stereotype.Repository
public class MessageRepository implements Repository<Message> {

  @Autowired
  private EntityManager entityManager;

  @Override
  public List<Message> findAll() {
    return null;
  }

  @Override
  public boolean delete(Message entity) {
    return false;
  }

  @Override
  public Message findById(long id) {
    return null;
  }

  @Override
  @Transactional
  public Message save(Message entity) {
    System.out.println(entity);
    return entityManager.merge(entity);
  }

  public List<MessageContact> getMessageContacts(String username){
    TypedQuery<MessageContact> query = entityManager.createNamedQuery("getAll_MessageContacts", MessageContact.class);
    return query.setParameter(1, username).setParameter(2, username).setParameter(3, username).setParameter(4, username).setParameter(5, username).setParameter(6, username).setParameter(7, username).getResultList();
  }

  @Transactional
  public int updateRead(String sender, String reciever){
    TypedQuery<MessageContact> query = entityManager.createNamedQuery("updateRead", MessageContact.class);

    System.out.println("do do thating");

    return query.setParameter(1, sender).setParameter(2, reciever).executeUpdate();
  }

  @Transactional
  public List<Message> getMessages(String name1, String name2){
    TypedQuery<Message> query = entityManager.createNamedQuery("getMessages", Message.class);

    return query.setParameter(1,name1).setParameter(2, name2).setParameter(3,name2).setParameter(4, name1).getResultList();
  }



  // NOTIFICATIONS
  public List<Message> findNotifications(String location) {
    TypedQuery<Message> namedQuery = entityManager.createNamedQuery("find_unread", Message.class);
    namedQuery.setParameter(1, false);
    namedQuery.setParameter(2, location);
    return namedQuery.getResultList();
   }
  @Transactional
  public List<Message> getMessagesPinned(String name1, String name2){
    TypedQuery<Message> query = entityManager.createNamedQuery("getMessagesPinned", Message.class);

    return query.setParameter(1,name1).setParameter(2, name2).setParameter(3,name2).setParameter(4, name1).getResultList();
  }
}
