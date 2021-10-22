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

    //Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHourIn() {
        return hourIn;
    }

    public void setHourIn(String hourIn) {
        this.hourIn = hourIn;
    }

    public String getHourOut() {
        return hourOut;
    }

    public void setHourOut(String hourOut) {
        this.hourOut = hourOut;
    }

    public int getVehiclesIn() {
        return vehiclesIn;
    }

    public void setVehiclesIn(int vehiclesIn) {
        this.vehiclesIn = vehiclesIn;
    }

    public int getVehiclesOut() {
        return vehiclesOut;
    }

    public void setVehiclesOut(int vehiclesOut) {
        this.vehiclesOut = vehiclesOut;
    }
}
