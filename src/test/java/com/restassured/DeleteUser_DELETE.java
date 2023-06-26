package com.restassured;

import static io.restassured.RestAssured.*;

//import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteUser_DELETE {

    @Test
    void deleteUser() {
        RestAssured.baseURI = "https://reqres.in/";
        RestAssured.basePath = "/api/users/2";

        // store the response to the variable
        Response response = given()
                .when()
                // no need to mention the URL here
                .delete()
                .then()
                .statusCode(204)
                .log().all()

                // to extract the response content
                .extract().response();
        System.out.println(response);
        // convert the response content to the json as string
        // String jsonAsString = response.asString();

        // compare the response content
        // Assert.assertEquals(jsonAsString.contains("successfully! deleted Records"),
        // true);
    }
}
