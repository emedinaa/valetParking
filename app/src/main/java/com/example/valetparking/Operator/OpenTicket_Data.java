package com.example.valetparking.Operator;

public class OpenTicket_Data {
    private String brand, model, year, color, plate, telephone, email, key, vehicle;

    //Constructor
    public OpenTicket_Data(String brand, String model, String year, String color, String plate, String telephone, String email, String key, String vehicle) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.plate = plate;
        this.telephone = telephone;
        this.email = email;
        this.key = key;
        this.vehicle = vehicle;
    }

    //Metodos Getter y Setter
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
}
