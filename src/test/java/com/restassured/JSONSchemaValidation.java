package com.restassured;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import io.restassured.module.jsv.JsonSchemaValidator;

public class JSONSchemaValidation {
    @Test
    void JSONSchemaValidationTest() {
        given()

                .when()
                .get("http://localhost:3000/store")
                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("StoreSchema.json"));
    }
}
