package be.helb.service;

import be.helb.DAO.GameDAO;
import be.helb.DAO.PlatformDAO;
import be.helb.model.Game;
import be.helb.model.Platform;
import be.helb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    GameDAO gameDAO;
    @Autowired
    PlatformDAO platformDAO;

    public GameService(GameDAO gameDAO, PlatformDAO platformDAO) {
        this.gameDAO = gameDAO;
        this.platformDAO = platformDAO;
    }

    public Game addGame(String name, String description, double price, LocalDate release_date, String platformName, User user) {
        Platform platform = platformDAO.findByName(platformName);
        Game game = new Game();
        game.setName(name);
        game.setDescription(description);
        game.setPrice(price);
        game.setReleaseDate(release_date);
        game.setId_user(Math.toIntExact(user.getId()));

        if (platform != null)
            game.setPlatform(platform);
        else
            game.setPlatform(null);
        return gameDAO.save(game);
    }

    public Game updateGame(Long id, Game game, User user) {
        Optional<Game> gameToUpdate = gameDAO.findById(id);
        if (gameToUpdate.isPresent()) {
            if (user.getId() == gameToUpdate.get().getId_user()){
                // Mettre à jour les propriétés du jeu
                Game updatedGame = gameToUpdate.get();
                updatedGame.setName(game.getName());
                updatedGame.setPrice(game.getPrice());
                updatedGame.setDescription(game.getDescription());
                updatedGame.setReleaseDate(game.getReleaseDate());
                //updatedGame.setId_user(game.getId_user());
                // Mettre à jour la plateforme associée (si elle a été modifiée)
                Platform platform = game.getPlatform();
                if (platform.getId() != 0) {
                    updatedGame.setPlatform(platform);
                }
                // Enregistrer les modifications
                return gameDAO.save(updatedGame);
            }
        }
        return null;
    }
    public Game updateGame(Long id, String newName, String newDescription, double newPrice, LocalDate newReleaseDate, String newPlatformName, User user) {
        Optional<Game> gameToUpdate = gameDAO.findById(id);
        Platform platform = platformDAO.findByName(newPlatformName);
        if (gameToUpdate.isPresent()) {
            if (user.getId() == gameToUpdate.get().getId_user()) {
                Game updatedGame = gameToUpdate.get();
                updatedGame.setName(newName);
                updatedGame.setDescription(newDescription);
                updatedGame.setPrice(newPrice);
                updatedGame.setReleaseDate(newReleaseDate);
                if (platform != null)
                    updatedGame.setPlatform(platform);
                else
                    updatedGame.setPlatform(null);

                return gameDAO.save(updatedGame);
            }
        }
        return null;
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

    public List<Game> getAllGamesOfAUser(User user) {
        List<Game> gameList = gameDAO.findAll();
        List<Game> finalList = new ArrayList<>();
        for (Game game: gameList) {
            if (game.getId_user() == user.getId()){
                finalList.add(game);
            }
        }
        return finalList;
    }



}
