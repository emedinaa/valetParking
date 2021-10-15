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

    //Getters
    public String getAdminName() {
        return adminName;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getPlaceType() {
        return placeType;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public String getPlacePhone() {
        return placePhone;
    }

    public String getPlaceFacebook() {
        return placeFacebook;
    }

    public String getPlaceInstagram() {
        return placeInstagram;
    }

    public String getPlaceTwitter() {
        return placeTwitter;
    }
}
