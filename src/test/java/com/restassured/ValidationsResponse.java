package com.restassured;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class ValidationsResponse {
    @Test(priority = 1)
    void validateStatusCode() {
        when().get("https://reqres.in/api/users?page=2").then().statusCode(200);
    }

    @Test(priority = 2)
    void getLogResponse() {
        when().get("https://reqres.in/api/users?page=2").then().statusCode(200).log().all();
    }

    @Test(priority = 3)
    void singleContentOfResponseBody() {
        given()
                .when()
                .get("https://reqres.in/api/users/7")
                .then()
                .body("data.first_name", Matchers.equalTo("Michael"));
    }

    @Test(priority = 4)
    void multipleContentOfResponseBody() {
        given()
                .when()
                .get("https://reqres.in/api/users/")
                .then()
                .body("data.first_name", Matchers.hasItems("George", "Emma"));
    }

    @Test(priority = 5)
    void setParamsAndHeaders() {
        given()
                .param("first_name", "sam")
                .header("myHeader", "Indian")
                .when()
                .get("https://reqres.in/api/users/")
                .then()
                .body("data.first_name", Matchers.hasItems("George", "Emma"))
                .log().all();
    }
}
