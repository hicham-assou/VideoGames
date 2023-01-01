package be.helb.controller;

import be.helb.DAO.GameDAO;
import be.helb.model.Game;
import be.helb.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    private GameService gameService;

    public Controller(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping("HelloWorld")
    public String hello(){
        return "Hello World";
    }
}
