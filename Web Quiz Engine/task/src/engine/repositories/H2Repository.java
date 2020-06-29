package engine.repositories;

import engine.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2Repository extends JpaRepository<Quiz, Long> {

}
