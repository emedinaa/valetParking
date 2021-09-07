package com.example.valetparking.Administrator;

public class Operators_Data {
    private String name;
    private int hourIn, hourOut, vehiclesIn, vehiclesOut;

    //Constructor
    public Operators_Data(String name, int hourIn, int hourOut, int vehiclesIn, int vehiclesOut) {
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

    public int getHourIn() {
        return hourIn;
    }

    public void setHourIn(int hourIn) {
        this.hourIn = hourIn;
    }

    public int getHourOut() {
        return hourOut;
    }

    public void setHourOut(int hourOut) {
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
