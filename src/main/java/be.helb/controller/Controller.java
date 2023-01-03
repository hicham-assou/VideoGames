package be.helb.controller;

import be.helb.DAO.GameDAO;
import be.helb.DAO.PlatformDAO;
import be.helb.model.Game;
import be.helb.model.Platform;
import be.helb.service.GameService;
import be.helb.service.PlatformService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    private GameService gameService;

    public Controller(GameService gameService){
        this.gameService = gameService;
    }

    @PostMapping("/add a new game")
    public ResponseEntity<Game> addGame(@RequestBody Game game, @RequestParam String platform_name) {
        Game savedGame = gameService.addGame(game, platform_name);
        return ResponseEntity.ok(savedGame);
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game) {
        Game updatedGame = gameService.updateGame(id, game);
        if (updatedGame != null) {
            return ResponseEntity.ok(updatedGame);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/get all games")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }



    @GetMapping("/find a game by name")
    public List<Game> getGameByName(@RequestParam String name) {
        return gameService.findByName(name);
    }

    @GetMapping("/find a game by price")
    public List<Game> getGameByPrice(@RequestParam double min_price, double max_price) {
        return gameService.findByPriceBetween(min_price, max_price);
    }

}
