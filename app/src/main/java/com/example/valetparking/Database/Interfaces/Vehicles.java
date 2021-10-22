package com.example.valetparking.Database.Interfaces;

import com.example.valetparking.Database.Models.Vehicle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Vehicles {

    //OPERATOR
        //CHECK IN
        @POST("vehicle/checkin/{id}")
        Call<Vehicle> createVehicle(@Body Vehicle vehicle, @Path("id") String id);

        //OPEN TICKET
        @GET("vehicle/open/{id}")
        Call<List<Vehicle>> getOpenVehicles(@Path("id") String id);

        //CHECK OUT
        @GET("vehicle/checkout/{id}/{token}")
        Call<Vehicle> getCloseVehicle(@Path("id") String id, @Path("token") int token);

        //CLOSE TICKET
        @GET("vehicle/close/{id}")
        Call<List<Vehicle>> getCloseVehicles(@Path("id") String id);

    //ADMINISTRATOR
        //TICKETS
        @GET("vehicle/tickets/{id}")
        Call<List<Vehicle>> getVehicles(@Path("id") String id);

}
