package com.example.valetparking.Administrator;

public class Operators_Data {
    private String name, hourIn, hourOut;
    private int vehiclesIn, vehiclesOut;

    //Constructor
    public Operators_Data(String name, String hourIn, String hourOut, int vehiclesIn, int vehiclesOut) {
        this.name = name;
        this.hourIn = hourIn;
        this.hourOut = hourOut;
        this.vehiclesIn = vehiclesIn;
        this.vehiclesOut = vehiclesOut;
    }

    //Metodos Getter y Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
