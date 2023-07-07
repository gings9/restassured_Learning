package com.restassured.APIChaining;

import static io.restassured.RestAssured.*;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetUser {
    @Test
    void testGetUser(ITestContext context) {
        int id = (Integer) context.getAttribute("user_id"); // this should come from createUser request

        String bearerToken = "614d8eb20a00f5daa0725cd325141ef74f45a3a80a8cc4cc80a107901d4e7525";

        given()
                .headers("Authorization", "Bearer " + bearerToken)
                .pathParam("id", id)
                .when()
                .get("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
