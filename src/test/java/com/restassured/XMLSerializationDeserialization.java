package com.restassured;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.hamcrest.Matchers;
import static io.restassured.RestAssured.*;

public class XMLSerializationDeserialization {
    // POST Request with Serialization
    @Test(priority = 1)
    public void testSerializationXML() {
        VideoGame game = new VideoGame();
        game.setId(11);
        game.setName("xyz123");
        game.setReleaseDate("2021-06-17");
        game.setReviewScore(90);
        game.setCategory("Driving");
        game.setRating("Five");

        given()
                .contentType("application/xml")
                .body(game)
                .when()
                .post("http://localhost:8080/app/videogames")
                .then()
                .log().all()
                .body(Matchers.equalTo("{\"status\":\"Record Added Successfully\"}"));
    }

    // GET Request with De-serialization
    @Test(priority = 2)
    public void testDeserializationXML() {
        VideoGame game = get("http://localhost:8080/app/videogames/11").as(VideoGame.class);
        System.out.println(game.toString());
        Assert.assertEquals(game.getId(), 21);
    }
}
