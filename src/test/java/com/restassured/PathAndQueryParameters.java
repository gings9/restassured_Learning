package com.restassured;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

public class PathAndQueryParameters {
    @Test
    void testPathAndQueryParameters() {
        given()
                .pathParam("myPath", "users") // path parameter
                .queryParam("page", 2) // query parameter
                .queryParam("id", 5) // query parameter
                .when()
                .get("https://reqres.in/api/{myPath}}") // with path variable
                .then()
                .statusCode(200)
                .log().all();
    }
}
