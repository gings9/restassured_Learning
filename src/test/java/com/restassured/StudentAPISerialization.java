package com.restassured;

import java.util.ArrayList;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class StudentAPISerialization {

    // POST Request with serialization
    @Test(priority = 1)
    public void createNewStudent() {
        ArrayList<String> coursesList = new ArrayList<String>();
        coursesList.add("Java");
        coursesList.add("Selenium");

        StudentRecord sr = new StudentRecord();
        sr.setId(101);
        sr.setFirstName("John");
        sr.setLastName("Deo");
        sr.setEmail("abc@xyz.com");
        sr.setProgramme("Computer Science");
        sr.setCourses(coursesList);

        given()
                .contentType(ContentType.JSON)
                .body(sr)
                .when()
                .post("http://localhost:8085/student")
                .then()
                .statusCode(201)
                .assertThat()
                .body("msg", Matchers.equalTo("Student added"));
    }

    // GET request with deserialization
    @Test(priority = 2)
    public void getStudentRecord() {
        StudentRecord sr = get("http://localhost:8085/student/101").as(StudentRecord.class);
        System.out.println(sr.getStudentRecord());
        Assert.assertEquals(sr.getId(), 101);
    }
}
