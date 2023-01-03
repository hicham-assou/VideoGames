package be.helb.DAO;

import be.helb.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameDAO extends JpaRepository<Game, Long> {

    List<Game> findByName(String name);

    List<Game> findByPriceBetween(double minPrice, double maxPrice);


}
