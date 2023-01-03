package be.helb.DAO;

import be.helb.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformDAO extends JpaRepository<Platform, Long> {
    Platform findByName(String name);

}