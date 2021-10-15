package com.example.valetparking.Database.Interfaces;

import com.example.valetparking.Database.Models.Vehicle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Vehicles {

    @GET("vehicle")
    Call<List<Vehicle>> getVehicles();

    @POST("vehicle")
    Call<List<Vehicle>> postVehicles();
}
