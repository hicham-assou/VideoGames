package be.helb.controller;

import be.helb.model.Game;
import be.helb.model.Platform;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameControllerIntegrationTest {
    @Test
    public void whenRequestGetAllGames_ThenOk() {
        Response response = get("/api/games/get all games");
        assertEquals(200, response.getStatusCode());
        List<Game> game = response.getBody().as(List.class);
        assertTrue(game.size() > 0);
    }

    @Test
    public void testFindByPriceBetween() throws Exception {
        // Envoyer une requête GET à l'URL de l'API
        String url = "http://localhost:8080/api/games/find a game by price?min_price=10&max_price=20";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List> responseEntity = new RestTemplate().exchange(url, HttpMethod.GET, entity, List.class);

        // Vérifier que la réponse a un code de statut 200 (OK)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Vérifier que la réponse contient une liste de jeux
        List<Game> games = responseEntity.getBody();
        assertTrue(games.size() > 0);
    }





}
