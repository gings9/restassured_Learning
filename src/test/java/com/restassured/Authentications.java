package com.restassured;

import static io.restassured.RestAssured.*;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class Authentications {
    // Basic Authentication
    @Test(priority = 1)
    void testBasicAuthentication() {
        given()
                .auth().basic("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", Matchers.equalTo(true))
                .log().all();
    }

    // Digest Authentication
    @Test(priority = 2)
    void testDigestAuthentication() {
        given()
                .auth().digest("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", Matchers.equalTo(true))
                .log().all();
    }

    // preemptive Authentication
    @Test(priority = 3)
    void testPreemptiveAuthentication() {
        given()
                .auth().preemptive().basic("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", Matchers.equalTo(true))
                .log().all();
    }

    // Bearer Token Authentication
    @Test(priority = 4)
    void testBearerTokenAuthentication() {
        String BearerToken = "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";
        given()
                .headers("Authorization", "Bearer " + BearerToken)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }

    // OAuth 1.0 Authentication
    @Test(priority = 5)
    void testOAuth1Authentication() {
        given()
                .auth().oauth("consumer_key", "consumer_secret", "accessToken", "tokenSecret")
                .when()
                .get("URL")
                .then()
                .statusCode(200)
                .log().all();
    }

    // OAuth 2.0 Authentication
    @Test(priority = 6)
    void testOAuth2Authentication() {
        given()
                .auth().oauth2("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")
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }

    // API Key Authentication
    @Test(priority = 6)
    void testAPIKeyAuthentication() {
        given()
                .queryParam("appid", "16fd09a98cdc89c1d3afe9a09c9e254c")
                .pathParam("mypath", "data/2.5/forecast/daily")
                .queryParam("q", "Delhi")
                .queryParam("units", "metric")
                .queryParam("cnt", "7")
                .when()
                .get("https://api.openweathermap.org/{mypath}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
