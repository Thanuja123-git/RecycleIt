package com.example.recycleit;

public class Courses {

    // variables for storing our data.
    private String companyname, code, dateofcoll;

    public Courses() {
        // empty constructor
        // required for Firebase.
    }

    // Constructor for all variables.
    public Courses(String companyname, String code, String dateofcoll) {
        this.companyname = companyname;
        this.code = code;
        this.dateofcoll = dateofcoll;
    }

    // getter methods for all variables.
    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCode() {
        return code;
    }

    // setter method for all variables.
    public void setCode(String code) {
        this.code = code;
    }

    public String getDateofcoll() {
        return dateofcoll;
    }

    public void setDateofcoll(String dateofcoll) {
        this.dateofcoll = dateofcoll;
    }
}
