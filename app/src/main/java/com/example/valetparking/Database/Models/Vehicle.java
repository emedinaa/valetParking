package com.example.valetparking.Database.Models;

import com.google.gson.annotations.SerializedName;

public class Vehicle {

    @SerializedName("brand")
    private String brand;

    @SerializedName("model")
    private String model;

    @SerializedName("year")
    private String year;

    @SerializedName("color")
    private String color;

    @SerializedName("plate")
    private String plate;

    @SerializedName("phone")
    private String phone;

    @SerializedName("email")
    private String email;

    @SerializedName("key")
    private String key;

    @SerializedName("vehicle")
    private String vehicle;

    //Getters
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public String getPlate() {
        return plate;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getKey() {
        return key;
    }

    public String getVehicle() {
        return vehicle;
    }
}
