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

    @SerializedName("code")
    private String code;

    @SerializedName("phone")
    private String phone;

    @SerializedName("email")
    private String email;

    @SerializedName("key")
    private String key;

    @SerializedName("vehicle")
    private String vehicle;

    @SerializedName("ticket")
    private String ticket;

    @SerializedName("operator_checkIn")
    private String operator_checkIn;

    @SerializedName("operator_checkOut")
    private String operator_checkOut;

    @SerializedName("note_cancel")
    private String note_cancel;

    @SerializedName("date")
    private String date;

    //Getters
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getOperator_checkIn() {
        return operator_checkIn;
    }

    public void setOperator_checkIn(String operator_checkIn) {
        this.operator_checkIn = operator_checkIn;
    }

    public String getOperator_checkOut() {
        return operator_checkOut;
    }

    public void setOperator_checkOut(String operator_checkOut) {
        this.operator_checkOut = operator_checkOut;
    }

    public String getNote_cancel() {
        return note_cancel;
    }

    public void setNote_cancel(String note_cancel) {
        this.note_cancel = note_cancel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
