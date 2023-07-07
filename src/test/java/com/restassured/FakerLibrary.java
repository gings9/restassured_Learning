package com.restassured;

//import static io.restassured.RestAssured.*;
//import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerLibrary {
    @Test
    void testFakerData() {
        Faker faker = new Faker();

        String fullName = faker.name().fullName();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        String username = faker.name().username();
        String password = faker.internet().password();

        String phoneNo = faker.phoneNumber().cellPhone();

        String email = faker.internet().safeEmailAddress();

        System.out.println("Full Name: " + fullName);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("User Name: " + username);
        System.out.println("Password: " + password);
        System.out.println("Phone No: " + phoneNo);
        System.out.println("Email: " + email);
    }
}
