package be.helb.controller;

import be.helb.model.Game;
import be.helb.model.Platform;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameControllerIntegrationTest {

   @Test // avant de clear and install on enleve cette methode sinn sa fonctionne pas
    public void whenRequestPostAddGame_ThenOk(){
        RestAssured.with().when().request("post", "/add a new game").then().statusCode(403);
    }

    /*@Test
    public void whenRequestGetAllMyGames_ThenOk(){
        RestAssured.with().when().request("GET", "/get all my games").then().statusCode(200);
    }*/
    @Test
    public void whenRequestGetAllMyGames_ThenOk() {
        Response response = get("/get all my games");
        assertEquals(200, response.getStatusCode());
        List<Game> game = response.getBody().as(List.class);
        assertTrue(game.size() > 0);
    }
}
