package be.helb.controller;

import io.restassured.RestAssured;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.Test;

public class GameControllerIntegrationTest {

   @Test // avant de clear and install on enleve cette methode sinn sa fonctionne pas
    public void whenRequestGet_ThenOk(){
        RestAssured.with().when().request("Get", "/HelloWorld").then().statusCode(200);
    }
}
