package com.m.attendancesystemmanagement.Model;

/**
 * Created by Monika Bhasin on 02-07-2018.
 */

public class Student {

    String id;
    String name;
    String email;
    String phone_number;
    String gender;
    String elective;

    public Student() {
    }

    public Student(String id, String name, String email, String phone_number, String gender, String elective) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.gender = gender;
        this.elective = elective;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getElective() {
        return elective;
    }

    public void setElective(String elective) {
        this.elective = elective;
    }
}
