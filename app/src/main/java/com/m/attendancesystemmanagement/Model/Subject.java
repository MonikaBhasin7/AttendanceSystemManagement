package com.m.attendancesystemmanagement.Model;

public class Subject {

    String subject_id;
    String subject_name;
    String elective_bool;

    public Subject() {
    }

    public Subject(String subject_id, String subject_name, String elective_bool) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.elective_bool = elective_bool;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getElective_bool() {
        return elective_bool;
    }

    public void setElective_bool(String elective_bool) {
        this.elective_bool = elective_bool;
    }
}
