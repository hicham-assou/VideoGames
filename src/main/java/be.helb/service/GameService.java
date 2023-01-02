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

    public List<Game> findByName(String name){
        List<Game> gameList = gameDAO.findByName(name);
        return gameList;
    }

    public List<Game> findByPriceBetween(double min_price, double max_price){
        List<Game> gameList = gameDAO.findByPriceBetween(min_price, max_price);
        return gameList;
    }
}
