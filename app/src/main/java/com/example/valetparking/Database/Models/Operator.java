package com.example.valetparking.Database.Models;

import com.google.gson.annotations.SerializedName;

public class Operator {

    @SerializedName("name")
    private String name;

    @SerializedName("phone")
    private String phone;

    @SerializedName("email")
    private String email;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("hourIn")
    private String hourIn;

    @SerializedName("hourOut")
    private String hourOut;

    @SerializedName("vehiclesIn")
    private int vehiclesIn;

    @SerializedName("vehiclesOut")
    private int vehiclesOut;

    //Getters

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHourIn() {
        return hourIn;
    }

    public String getHourOut() {
        return hourOut;
    }

    public int getVehiclesIn() {
        return vehiclesIn;
    }

    public int getVehiclesOut() {
        return vehiclesOut;
    }
}
