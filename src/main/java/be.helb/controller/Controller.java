package be.helb.controller;

import be.helb.DAO.GameDAO;
import be.helb.model.Game;
import be.helb.service.GameService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    private GameService gameService;
    @Autowired
    private GameDAO gameDAO;


    public Controller(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping("HelloWorld")
    public String hello(){
        return "Hello World";
    }

    @PostMapping("/add a new game")
    public ResponseEntity<Game> addGame(@RequestBody Game game) {
        Game savedGame = gameDAO.save(game);
        return ResponseEntity.ok(savedGame);
    }

    @PutMapping("/update game/{name}")
    public ResponseEntity<Game> updateGame(@RequestBody Game game) {
        Game savedGame = gameDAO.save(game);
        return ResponseEntity.ok(savedGame);
    }
}
