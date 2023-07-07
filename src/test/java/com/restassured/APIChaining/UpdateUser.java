package com.restassured.APIChaining;

import static io.restassured.RestAssured.*;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {
    @Test
    void testUpdateUser(ITestContext context) {
        int id = (Integer) context.getAttribute("user_id"); // this should come from CreateUser

        // create a request body data
        Faker faker = new Faker();
        JSONObject data = new JSONObject();

        // 3 fields except Gender will be updated
        data.put("name", faker.name().fullName());
        data.put("gender", "Male");
        data.put("email", faker.internet().emailAddress());
        data.put("status", "Active");

        // create a bearer token variable for easy use
        String bearerToken = "614d8eb20a00f5daa0725cd325141ef74f45a3a80a8cc4cc80a107901d4e7525";

        // create a Id integer to store the id from JSON Response
        given()
                .headers("Authorization", "Bearer " + bearerToken)
                .pathParam("id", id) // added apart from CreateUser
                .contentType("application/json")
                .body(data.toString())
                .when()
                .put("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
