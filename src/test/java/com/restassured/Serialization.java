package com.restassured;

import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Serialization {
    @Test
    void convertPOJO2JSON() throws JsonProcessingException {
        Student stupojo = new Student(); // Create a POJO object
        stupojo.setName("Scott");
        stupojo.setLocation("France");
        stupojo.setPhone("123456");
        String coursesArr[] = { "C", "C++" };
        stupojo.setCourses(coursesArr);

        // convert POJO TO JSON --> Serialization
        ObjectMapper mapper = new ObjectMapper();
        String JSONData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);
        System.out.println(JSONData);

        // convert JSON TO POJO --> De-Serialization
        String JSONValue = "{\r\n"
                + " \"name\" : \"Scott\",\r\n"
                + " \"location\" : \"France\",\r\n"
                + " \"phone\" : \"123456\",\r\n"
                + " \"courses\" : [ \"C\", \"C++\" ]\r\n"
                + "}";

        ObjectMapper mapperObj = new ObjectMapper();
        Student POJOObj = mapperObj.readValue(JSONValue, Student.class);
        System.out.println(POJOObj.getName());
        System.out.println(POJOObj.getLocation());
        System.out.println(POJOObj.getPhone());
        System.out.println(POJOObj.getCourses()[0]);
        System.out.println(POJOObj.getCourses()[1]);
    }
}
