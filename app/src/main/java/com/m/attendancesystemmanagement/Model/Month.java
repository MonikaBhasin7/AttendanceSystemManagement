package com.m.attendancesystemmanagement.Model;

public class Month {

    public String month_number;
    public String month_name;

    public Month() {
    }

    public Month(String month_number, String month_name) {
        this.month_number = month_number;
        this.month_name = month_name;
    }

    public String getMonth_number() {
        return month_number;
    }

    public void setMonth_number(String month_number) {
        this.month_number = month_number;
    }

    public String getMonth_name() {
        return month_name;
    }

    public void setMonth_name(String month_name) {
        this.month_name = month_name;
    }
}
