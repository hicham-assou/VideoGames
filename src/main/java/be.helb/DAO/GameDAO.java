package be.helb.DAO;

import be.helb.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GameDAO extends JpaRepository<Game, Long> {
    List<Game> findByName(String name);
}
