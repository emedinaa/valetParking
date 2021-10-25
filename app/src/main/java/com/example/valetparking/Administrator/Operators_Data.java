package com.example.valetparking.Administrator;

public class Operators_Data {
    private String username, hourIn, hourOut;
    private int vehiclesIn, vehiclesOut;

    //Constructor
    public Operators_Data(String username, String hourIn, String hourOut, int vehiclesIn, int vehiclesOut) {
        this.username = username;
        this.hourIn = hourIn;
        this.hourOut = hourOut;
        this.vehiclesIn = vehiclesIn;
        this.vehiclesOut = vehiclesOut;
    }

    //Metodos Getter y Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
