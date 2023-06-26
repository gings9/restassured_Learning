package com.restassured;

import java.util.HashMap;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

public class CreateUser_POST {

    @Test
    void CreateUser() {
        HashMap<String, String> reqBody = new HashMap<String, String>();
        reqBody.put("name", "Gings");
        reqBody.put("job", "trainer");

        given()
                .contentType("application/json")
                .body(reqBody)

                .when()
                .post("https://reqres.in/api/users")

                .then()
                .statusCode(201)
                .log().all();
    }
}
