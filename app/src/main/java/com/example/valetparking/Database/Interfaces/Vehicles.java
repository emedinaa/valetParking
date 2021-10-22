package com.example.valetparking.Database.Interfaces;

import com.example.valetparking.Database.Models.Vehicle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Vehicles {

    //OPERATOR
        //CHECK IN
        @POST("vehicle/checkin/{id}")
        Call<Vehicle> checkIn(@Body Vehicle vehicle, @Path("id") String id);

        //OPEN TICKET
        @GET("vehicle/open/{id}")
        Call<List<Vehicle>> openTicket(@Path("id") String id);

        //CHECK OUT
        @GET("vehicle/checkout/{token}")
        Call<Vehicle> checkOut(@Path("token") int token);

        //UPDATE TICKET CLOSE
        @PUT("vehicle/checkout/close/{id}/{token}")
        Call<Vehicle> updateTicketClose(@Path("id") String id, @Path("token") String plate);

        //CLOSE TICKET
        @GET("vehicle/close/{id}")
        Call<List<Vehicle>> getCloseVehicles(@Path("id") String id);

    //ADMINISTRATOR
        //TICKETS
        @GET("vehicle/tickets/{id}")
        Call<List<Vehicle>> getVehicles(@Path("id") String id);

}
