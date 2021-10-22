package com.example.valetparking.Database.Models;

import com.google.gson.annotations.SerializedName;

public class Administrator {

    @SerializedName("adminName")
    private String adminName;

    @SerializedName("adminPhone")
    private String adminPhone;

    @SerializedName("adminEmail")
    private String adminEmail;

    @SerializedName("adminUsername")
    private String adminUsername;

    @SerializedName("adminPassword")
    private String adminPassword;

    @SerializedName("placeName")
    private String placeName;

    @SerializedName("placeType")
    private String placeType;

    @SerializedName("placeDescription")
    private String placeDescription;

    @SerializedName("placePhone")
    private String placePhone;

    @SerializedName("placeFacebook")
    private String placeFacebook;

    @SerializedName("placeInstagram")
    private String placeInstagram;

    @SerializedName("placeTwitter")
    private String placeTwitter;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    //Getters
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public void setPlaceDescription(String placeDescription) {
        this.placeDescription = placeDescription;
    }

    public String getPlacePhone() {
        return placePhone;
    }

    public void setPlacePhone(String placePhone) {
        this.placePhone = placePhone;
    }

    public String getPlaceFacebook() {
        return placeFacebook;
    }

    public void setPlaceFacebook(String placeFacebook) {
        this.placeFacebook = placeFacebook;
    }

    public String getPlaceInstagram() {
        return placeInstagram;
    }

    public void setPlaceInstagram(String placeInstagram) {
        this.placeInstagram = placeInstagram;
    }

    public String getPlaceTwitter() {
        return placeTwitter;
    }

    public void setPlaceTwitter(String placeTwitter) {
        this.placeTwitter = placeTwitter;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
