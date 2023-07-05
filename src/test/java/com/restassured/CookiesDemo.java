package com.restassured;

import org.testng.annotations.Test;

//import io.restassured.http.Cookie;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.util.Map;

public class CookiesDemo {
    // @Test(priority = 1)
    void testCookies() {
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .cookie("AEC", "Ad49MVHqJvIPTzX346Zz4Zg_uJ_qc3KnwjJTa3OsQuJZjb6kWmZNAsdIPxc")
                .log().all();
    }

    @Test(priority = 2)
    void getCookiesInfo() {
        Response res = given()
                .when()
                .get("https://www.google.com/");

        // get single cookie information
        // String Cookie_Value = res.getCookie("AEC");
        // System.out.println("The Value of Cookie is: " + Cookie_Value);

        // get all cookies information
        Map<String, String> Cookie_Values = res.getCookies();

        // to print all cookies name only
        System.out.println(Cookie_Values.keySet());

        // to print all cookies name and values
        for (String k : Cookie_Values.keySet()) {
            String Cookie_Value = res.getCookie(k);
            System.out.println(k + " = " + Cookie_Value);
        }
    }
}
