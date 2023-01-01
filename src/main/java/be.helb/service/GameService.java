package be.helb.service;

import be.helb.DAO.GameDAO;
import be.helb.model.Game;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    GameDAO gameDAO;
    public GameService(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }

    public List<Game> getAllGames(){
        List<Game> gameList = gameDAO.findAll();
        return gameList;
    }
}
