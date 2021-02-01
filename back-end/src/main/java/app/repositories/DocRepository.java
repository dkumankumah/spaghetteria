package app.repositories;

import app.models.Doc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocRepository  extends JpaRepository<Doc,Integer> {
}


