package com.restassured;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class HeadersDemo {
    // @Test(priority = 1)
    void testHeaders() {
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .and()
                .header("Content-Encoding", "gzip")
                .and()
                .header("Server", "gws");
    }

    @Test(priority = 1)
    void getHeaders() {
        Response res = given()
                .when()
                .get("https://www.google.com/");

        // get single header information
        // String Header_Value = res.getHeader("Content-Type");
        // System.out.println("Header Value is " + Header_Value);

        // get all the headers information
        Headers headers = res.getHeaders();
        for (Header h : headers) {
            System.out.println(h.getName() + " = " + h.getValue());
        }
    }
}
