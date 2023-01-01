package be.helb.service;

import be.helb.DAO.GameDAO;
import be.helb.model.Game;
import org.easymock.EasyMock;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameServiceTestExo {
    private GameDAO gameDAO;
    private GameService gameService;

    @Test
    public void testGetAllGames(){
        List<Game> gameList = List.of(new Game("fortnite"), new Game("fifa 23"));
        gameDAO = EasyMock.mock(GameDAO.class);
        EasyMock.expect(gameDAO.findAll()).andReturn(gameList);
        gameService = new GameService(gameDAO);
        EasyMock.replay(gameDAO);
        List<Game> result = gameService.getAllGames();
        EasyMock.verify(gameDAO);
        assertEquals(3, result.size());


    }
}
