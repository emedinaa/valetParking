package com.example.valetparking.Database.Interfaces;

import com.example.valetparking.Database.Models.Administrator;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Administrators {

    //CREATE ACCOUNT
    @POST("administrator")
    Call<Administrator> createAccount(@Body Administrator administrator);

    //READ ONE
    @GET("administrator/{id}")
    Call<Administrator> getAdministrator(@Path("id") String id);
}
