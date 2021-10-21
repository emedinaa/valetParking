package com.example.valetparking.Database.Interfaces;

import com.example.valetparking.Database.Models.Vehicle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Vehicles {

    //CHECK IN
    //@FormUrlEncoded
    @POST("vehicle/opened/{id}")
    Call<Vehicle> createVehicle(@Body Vehicle vehicle, @Path("id") String id);

    //OPEN TICKET
    @GET("vehicle/opened")
    Call<List<Vehicle>> getOpenVehicles();

    //CHECK OUT
    @FormUrlEncoded
    @GET("vehicle/closed")
    Call<Vehicle> getCloseVehicle(@Field("token") int token);

    //CLOSE TICKET
    @GET("vehicle/closed/{id}")
    Call<List<Vehicle>> getCloseVehicles(@Path("id") String id);

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
