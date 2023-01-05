package be.helb.controller;

import be.helb.DAO.GameDAO;
import be.helb.DAO.PlatformDAO;
import be.helb.DAO.UserDao;
import be.helb.model.Game;
import be.helb.model.Platform;
import be.helb.model.User;
import be.helb.service.GameService;
import be.helb.service.PlatformService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class Controller {
    private GameService gameService;

    @Autowired
    private UserDao userDao;

    public Controller(GameService gameService, UserDao userDao){
        this.gameService = gameService;
        this.userDao = userDao;
    }
   @PostMapping("add a new game")
   public ResponseEntity<Game> addGame(@RequestParam String name, @RequestParam String description, @RequestParam double price, @RequestParam LocalDate release_date, @RequestParam String platform_name) {
       User user = getUserConneced();
       // Ajouter l'utilisateur à id_user du jeu
       //game.setId_user(Math.toIntExact(user.getId()));
       Game savedGame = gameService.addGame(name, description, price, release_date, platform_name, user);
       return ResponseEntity.ok(savedGame);
   }

    @PutMapping("update a game/{id}")
    @ApiOperation("you must select the id of a game that is yours")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestParam String new_name, @RequestParam String new_description, @RequestParam double new_price, @RequestParam LocalDate new_release_date, @RequestParam String new_platform_name) {
        Game updatedGame = gameService.updateGame(id, new_name, new_description, new_price, new_release_date, new_platform_name, getUserConneced());
        //Game updatedGame = null;
        if (updatedGame != null) {
            return ResponseEntity.ok(updatedGame);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }


    private User getUserConneced(){
        // Récupérer le nom d'utilisateur de l'utilisateur connecté
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Récupérer l'utilisateur à partir de la base de données en utilisant le nom d'utilisateur
        User user = userDao.findByUsername(username);
        return user;
    }

    @GetMapping("get all my games")
    @ApiOperation("display all the games you own")
    public List<Game> getAllMyGames() {
        return gameService.getAllGamesOfAUser(getUserConneced());
    }

    @GetMapping("get all games")
    @ApiOperation("display all the games")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }



    @GetMapping("find a game by name")
    public List<Game> getGameByName(@RequestParam String name) {
        return gameService.findByName(name);
    }

    @GetMapping("find a game by price")
    @ApiOperation("you must specify the price range you want to display")
    public List<Game> getGameByPrice(@RequestParam double min_price, double max_price) {
        return gameService.findByPriceBetween(min_price, max_price);
    }

}
