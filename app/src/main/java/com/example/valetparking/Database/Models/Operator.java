package com.example.valetparking.Database.Models;

public class Operator {

    private String name;

    private String phone;

    private String email;

    private String username;

    private String password;

    private String hourIn;

    private String hourOut;

    private int vehiclesIn;

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
