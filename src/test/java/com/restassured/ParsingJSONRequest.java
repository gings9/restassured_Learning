package com.restassured;

import static io.restassured.RestAssured.*;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJSONRequest {
    @Test(priority = 1)
    void testJSONResponse() {
        // Direct Approach
        // given()
        // .contentType(ContentType.JSON)
        // .when()
        // .get("http://localhost:3000/store")
        // .then()
        // .statusCode(200)
        // .header("Content-Type", "application/json; charset=utf-8")
        // .body("book[3].title", Matchers.equalTo("The Lord of the Rings"));

        // Variable Approach
        Response res = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/store");

        // Validation 1
        Assert.assertEquals(res.getStatusCode(), 200);

        // Validation 2
        Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");

        // Validation 3
        // convert the JSON response for Book Name into a string
        String bookName = res.jsonPath().get("book[3].title").toString();
        Assert.assertEquals(bookName, "The Lord of the Rings");
    }

    @Test(priority = 2)
    void testJSONResponseDeeply() {
        Response res = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/store");

        // JSON Object Class
        // convert the Response into JSON Object type as a string

        JSONObject jo = new JSONObject(res.asString());

        for (int i = 0; i < jo.getJSONArray("book").length(); i++) {
            String bookName = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
            System.out.println(bookName);
        }
    }
}
