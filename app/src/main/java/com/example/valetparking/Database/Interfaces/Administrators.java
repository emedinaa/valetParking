package com.example.valetparking.Database.Interfaces;

import com.example.valetparking.Database.Models.Administrator;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Administrators {

    //CREATE ACCOUNT
    @POST("administrator")
    Call<Administrator> createAccount(@Body Administrator administrator);
}
