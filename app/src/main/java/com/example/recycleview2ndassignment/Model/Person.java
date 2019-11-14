package com.example.recycleview2ndassignment.Model;

import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private String dob;
    private String gender;
    private String country;
    private String phone;
    private String email;
    private int image;

    public Person(String name, String dob, String gender, String country, String phone, String email, int image) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.country = country;
        this.phone = phone;
        this.email = email;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getImage() {
        return image;
    }

}
