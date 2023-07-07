package com.restassured;

import static io.restassured.RestAssured.*;
import java.util.List;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLRequest {
    // @Test
    void testXMLResponse() {
        given()
                .when()
                .get("http://restapi.adequateshop.com//api/Traveler?page=1")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/xml; charset=utf-8")
                .body("TravelerinformationResponse.page", Matchers.equalTo("1"))
                .body("TravelerinformationResponse.travelers.Travelerinformation[0].name",
                        Matchers.equalTo("Developer"));
    }

    // Variable Approach
    // @Test(priority = 1)
    void testXMLResponsebody() {
        Response res = given()
                .when()
                .get("http://restapi.adequateshop.com//api/Traveler?page=1");

        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");

        String PageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();
        Assert.assertEquals(PageNo, "1");

        String TravelerName = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name")
                .toString();
        Assert.assertEquals(TravelerName, "Developer");
    }

    // Specific Validations
    @Test(priority = 1)
    void testTheXMLResponsebody() {
        Response res = given()
                .when()
                .get("http://restapi.adequateshop.com//api/Traveler?page=1");

        // Validate total Number of Travelers
        XmlPath xmlObject = new XmlPath(res.asString());

        List<String> travelers = xmlObject.getList("TravelerinformationResponse.travelers.Travelerinformation");

        Assert.assertEquals(travelers.size(), 10);

        // Verify specific traveler name is present in the response
        List<String> TravelerName = xmlObject.getList("TravelerinformationResponse.travelers.Travelerinformation.name");

        boolean status = false;
        for (String name : TravelerName) {
            if (name.equals("Developer")) {
                status = true;
                break;
            }
        }
        Assert.assertEquals(status, false);
    }
}
