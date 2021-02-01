package app;

import app.repositories.TaskRepoJpa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootApplication
public class BackEndApplication {

  @Autowired
  EntityManager entityManager;

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired
  TaskRepoJpa repo;
	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);
	}

}

