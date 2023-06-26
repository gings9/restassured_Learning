package com.restassured;

import java.util.ArrayList;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;

public class JSONWithoutSerialization {

    HashMap map = new HashMap();

    // POST request to create a Student record
    @Test
    public void createNewStrudent() {
        map.put("Id", "101");
        map.put("FirstName", "Pavan");
        map.put("LastName", "Kumar");
        map.put("Email", "abc@xyz.com");
        map.put("Programme", "Manager");

        ArrayList<String> courseList = new ArrayList<String>();
        courseList.add("Java");
        courseList.add("Selenium");

        map.put("courses", courseList);

        given()
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .post("http://localhost:8085/student")
                .then()
                .statusCode(201)
                .assertThat()
                .body("msg", Matchers.equalTo("Student added"));
    }

    // Get a Student record by JSON Request
    @Test(priority = 2)
    public void getStudentrecord() {
        when()
                .get("http://localhost:8085/student/101")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", Matchers.equalTo(101))
                .log().all();
    }
}
