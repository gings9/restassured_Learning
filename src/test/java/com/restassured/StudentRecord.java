package com.restassured;

import java.util.List;

public class StudentRecord {
    public Integer id;
    public String FirstName;
    public String LastName;
    public String Email;
    public String Programme;

    List<String> courses;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return this.LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getProgramme() {
        return this.Programme;
    }

    public void setProgramme(String Programme) {
        this.Programme = Programme;
    }

    public List<String> getCourses() {
        return this.courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public String getStudentRecord() {
        return (this.id + "" + this.FirstName + "" + this.LastName + "" + this.Email + "" + this.Programme + ""
                + this.courses);
    }
}
