package com.restassured;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class ValidationsResponseXML {

    @Test(priority = 1)
    void testSingleContent() {
        when()
                .get("http://thomas-bayer.com/sqlrest/CUSTOMER/15/")
                .then()
                .body("CUSTOMER.ID", Matchers.equalTo("15"))
                .log().all();
    }

    @Test(priority = 2)
    void testMultipleContent() {
        when()
                .get("http://thomas-bayer.com/sqlrest/CUSTOMER/15/")
                .then()
                .body("CUSTOMER.ID", Matchers.equalTo("15"))
                .body("CUSTOMER.FIRSTNAME", Matchers.equalTo("Bill"))
                .body("CUSTOMER.LASTNAME", Matchers.equalTo("Clancy"))
                .body("CUSTOMER.STREET", Matchers.equalTo("319 Upland Pl."))
                .body("CUSTOMER.CITY", Matchers.equalTo("Seattle"))
                .log().all();
    }

    @Test(priority = 3)
    void testMultipleContentInOneGo() {
        when()
                .get("http://thomas-bayer.com/sqlrest/CUSTOMER/15/")
                .then()
                .body("CUSTOMER.text()", Matchers.equalTo("15BillClancy319 Upland Pl.Seattle"))
                .log().all();
    }

    @Test(priority = 4)
    void testUsingXPath() {
        when()
                .get("http://thomas-bayer.com/sqlrest/CUSTOMER/15/")
                .then()
                // .body("CUSTOMER.FIRSTNAME", Matchers.equalTo("Bill"))
                .body(Matchers.hasXPath("/CUSTOMER/FIRSTNAME", Matchers.containsString("Bill")))
                .log().all();
    }

    @Test(priority = 5)
    void testUsingXPath1() {
        when()
                .get("http://thomas-bayer.com/sqlrest/INVOICE/")
                .then()
                // .body("CUSTOMER.FIRSTNAME", Matchers.equalTo("Bill"))
                .body(Matchers.hasXPath("/INVOICEList/INVOICE[text()='30']"))
                .log().all();
    }
}
