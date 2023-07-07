package com.restassured.APIChaining;

import static io.restassured.RestAssured.*;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

public class CreateUser {
    @Test
    void testCreateUser(ITestContext context) {
        // create a request body data
        Faker faker = new Faker();
        JSONObject data = new JSONObject();

        data.put("name", faker.name().fullName());
        data.put("gender", "Male");
        data.put("email", faker.internet().emailAddress());
        data.put("status", "inactive");

        // create a bearer token variable for easy use
        String bearerToken = "614d8eb20a00f5daa0725cd325141ef74f45a3a80a8cc4cc80a107901d4e7525";

        // create a Id integer to store the id from JSON Response
        int id = given()
                .headers("Authorization", "Bearer " + bearerToken)
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("https://gorest.co.in/public/v2/users")
                .jsonPath().getInt("id");
        System.out.println("Generated Id is: " + id);

        // set id integer to the attribute in context as user_id
        context.setAttribute("user_id", id);
        // with this, we are able to access the id variable in other methods
    }
}
