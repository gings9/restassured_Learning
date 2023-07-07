package com.restassured;

import static io.restassured.RestAssured.*;
import java.io.File;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class FileUpload {
    // Single file upload
    // @Test
    void SingleFileUploadTest() {
        File myFile = new File("C:\\AutomationPractice\\Test1.txt");

        given()
                .multiPart("file", myFile)
                .contentType("multipart/form-data")
                .when()
                .post("http://localhost:8080/uploadFile")
                .then()
                .statusCode(200)
                .body("filename", Matchers.equalTo("Test1.txt"))
                .log().all();
    }

    // Multiple files upload
    @Test
    void MultipleFileUploadTest() {
        File myFile1 = new File("C:\\AutomationPractice\\Test1.txt");
        File myFile2 = new File("C:\\AutomationPractice\\Test2.txt");

        given()
                .multiPart("files", myFile1)
                .multiPart("files", myFile2)
                .contentType("multipart/form-data")
                .when()
                .post("http://localhost:8080/uploadFiles")
                .then()
                .statusCode(200)
                .body("[0].filename", Matchers.equalTo("Test1.txt"))
                .body("[1].filename", Matchers.equalTo("Test2.txt"))
                .log().all();
    }

    // Single file Download
    @Test
    void SingleFileDownloadTest() {

        given()
                .when()
                .get("http://localhost:8080/downloadFile/Test1.txt")
                .then()
                .statusCode(200)
                .log().all();
    }
}
