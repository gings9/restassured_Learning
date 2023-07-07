package com.restassured;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import io.restassured.matcher.RestAssuredMatchers;

public class XMLSchemaValidation {
    @Test
    void testXMLSchemaValidation() {
        given()

                .when()
                .get("http://restapi.adequateshop.com/api/Traveler")
                .then()
                .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("traveler.xsd"));
    }
}
