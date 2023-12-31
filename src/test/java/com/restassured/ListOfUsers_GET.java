package com.restassured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class ListOfUsers_GET {

    @Test
    void getUsersList() {
        given()

                .when()
                .get("https://reqres.in/api/users?page=2")

                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();
    }
}
