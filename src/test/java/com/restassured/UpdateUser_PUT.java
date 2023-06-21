package com.restassured;

import java.util.HashMap;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

public class UpdateUser_PUT {

    int id;

    @Test(priority = 1)
    void CreateUser() {
        HashMap reqBody = new HashMap();
        reqBody.put("name", "Gings");
        reqBody.put("job", "trainer");

        // store the id value to id variable
        id = given()
                .contentType("application/json")
                .body(reqBody)

                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
    }

    // will Run only when create user is successful
    @Test(priority = 2, dependsOnMethods = { "CreateUser" })
    void updateUser() {
        HashMap reqBody = new HashMap();
        reqBody.put("name", "Gings");
        reqBody.put("job", "Coder");

        given()
                .contentType("application/json")
                .body(reqBody)

                // concat the id variable
                .when()
                .put("https://reqres.in/api/users/" + id)

                .then()
                .statusCode(200)
                .log().all();
    }
}
