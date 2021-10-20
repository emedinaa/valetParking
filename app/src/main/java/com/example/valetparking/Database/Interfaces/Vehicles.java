package com.example.valetparking.Database.Interfaces;

import com.example.valetparking.Database.Models.Vehicle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Vehicles {

    @GET("vehicle/")
    Call<List<Vehicle>> getOpenVehicles();

    //@FormUrlEncoded
    @POST("vehicle/{id}")
    Call<Vehicle> createVehicle(@Body Vehicle vehicle, @Path("id") String id);

    /*
    @Field("brand") String brand,
            @Field("model") String model,
            @Field("year") String year,
            @Field("color") String color,
            @Field("plate") String plate,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("key") String key,
            @Field("vehicle") String vehicle
     */
}
