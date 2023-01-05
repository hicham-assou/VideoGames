package be.helb.service;

import be.helb.DAO.GameDAO;
import be.helb.DAO.PlatformDAO;
import be.helb.model.Game;
import be.helb.model.Platform;
import be.helb.model.User;
import org.easymock.EasyMock;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameServiceTestExo {
    private GameDAO gameDAO;
    private GameService gameService;
    private PlatformDAO platformDAO;

    @Test
    public void testGetAllGames(){
        List<Game> gameList = List.of(new Game("fortnite"), new Game("fifa 23"));
        gameDAO = EasyMock.mock(GameDAO.class);
        platformDAO = EasyMock.mock(PlatformDAO.class);
        EasyMock.expect(gameDAO.findAll()).andReturn(gameList);
        gameService = new GameService(gameDAO, platformDAO);
        EasyMock.replay(gameDAO);
        List<Game> result = gameService.getAllGames();
        EasyMock.verify(gameDAO);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindByName() {
        gameDAO = EasyMock.mock(GameDAO.class);
        EasyMock.expect(gameDAO.findByName("Fortnite")).andReturn(List.of(new Game("Fortnite")));
        gameService = new GameService(gameDAO, platformDAO);
        EasyMock.replay(gameDAO);

        List<Game> result = gameService.findByName("Fortnite");

        EasyMock.verify(gameDAO);
        assertEquals(1, result.size());
    }

    @Test
    public void testFindByPriceBetween() {
        gameDAO = EasyMock.mock(GameDAO.class);
        Game game1 = new Game("game1");
        game1.setPrice(15.0);
        Game game2 = new Game("game2");
        game1.setPrice(18.0);
        // Configuration du comportement du mock : la méthode findByPriceBetween doit renvoyer la liste attendue
        EasyMock.expect(gameDAO.findByPriceBetween(10, 20)).andReturn(List.of(game1, game2));
        // Création de l'objet gameService à tester en utilisant le mock de GameDAO
        gameService = new GameService(gameDAO, platformDAO);
        // Activation du mock (obligatoire avant de pouvoir utiliser le mock)
        EasyMock.replay(gameDAO);

        // Appel de la méthode findByPriceBetween sur l'objet gameService
        List<Game> result = gameService.findByPriceBetween(10, 20);

        // Vérification que le mock a été utilisé comme prévu
        EasyMock.verify(gameDAO);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetAllGamesOfAUser() {
        gameDAO = EasyMock.mock(GameDAO.class);
        Game game1 = new Game("game1");
        game1.setId_user(1);
        Game game2 = new Game("game2");
        game2.setId_user(2);
        Game game3 = new Game("game3");
        game3.setId_user(1);
        EasyMock.expect(gameDAO.findAll()).andReturn(List.of(game1, game2, game3));

        gameService = new GameService(gameDAO, platformDAO);
        EasyMock.replay(gameDAO);

        User user = new User();
        user.setId(1L);
        List<Game> result = gameService.getAllGamesOfAUser(user);

        EasyMock.verify(gameDAO);
        assertEquals(2, result.size());
    }
    




}
