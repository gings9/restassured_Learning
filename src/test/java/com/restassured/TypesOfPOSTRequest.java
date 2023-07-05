package com.restassured;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class TypesOfPOSTRequest {

    // 1. POST request body using HashMap

    // @Test(priority = 1)
    void testPOSTusingHashMap() {
        HashMap<String, Object> reqBody = new HashMap<String, Object>();
        reqBody.put("name", "Scott");
        reqBody.put("location", "France");
        reqBody.put("phone", "123456");

        String courseArr[] = { "C", "C++" };
        reqBody.put("courses", courseArr);

        given()
                .contentType("application/json")
                .body(reqBody)
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name", Matchers.equalTo("Scott"))
                .body("location", Matchers.equalTo("France"))
                .body("phone", Matchers.equalTo("123456"))
                .body("courses[0]", Matchers.equalTo("C"))
                .body("courses[1]", Matchers.equalTo("C++"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();
    }

    // 2. POST request body using org.json library

    // @Test(priority = 1)
    void testPOSTusingJSONLibrary() {
        JSONObject data = new JSONObject();
        data.put("name", "Scott");
        data.put("location", "France");
        data.put("phone", "123456");

        String courseArr[] = { "C", "C++" };
        data.put("courses", courseArr);

        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name", Matchers.equalTo("Scott"))
                .body("location", Matchers.equalTo("France"))
                .body("phone", Matchers.equalTo("123456"))
                .body("courses[0]", Matchers.equalTo("C"))
                .body("courses[1]", Matchers.equalTo("C++"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();
    }

    // 3. POST request body using POJO Class

    // @Test(priority = 1)
    void testPOSTusingPOJO() {
        POJO_POST data = new POJO_POST();
        data.setName("Scott");
        data.setLocation("France");
        data.setPhone("123456");

        String coursesArr[] = { "C", "C++" };
        data.setCourses(coursesArr);

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name", Matchers.equalTo("Scott"))
                .body("location", Matchers.equalTo("France"))
                .body("phone", Matchers.equalTo("123456"))
                .body("courses[0]", Matchers.equalTo("C"))
                .body("courses[1]", Matchers.equalTo("C++"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();
    }

    // 4. POST request body using POJO Class

    @Test(priority = 1)
    void testPOSTusingExternalJSONFile() throws FileNotFoundException {
        File fObject = new File(".\\PostBody.json");
        FileReader reader = new FileReader(fObject);
        JSONTokener jsonTokener = new JSONTokener(reader);
        JSONObject data = new JSONObject(jsonTokener);

        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name", Matchers.equalTo("Scott"))
                .body("location", Matchers.equalTo("France"))
                .body("phone", Matchers.equalTo("123456"))
                .body("courses[0]", Matchers.equalTo("C"))
                .body("courses[1]", Matchers.equalTo("C++"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();
    }

    // deleting student record
    @Test(priority = 2)
    void deleteStudent() throws Exception {
        given()
                .when()
                .delete("http://localhost:3000/students/3")
                .then()
                .statusCode(200);
    }
}
