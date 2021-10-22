package com.example.valetparking.Database.Interfaces;

import com.example.valetparking.Database.Models.Operator;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Operators {

    //CREATE OPERATOR
    @POST("operator/create/{id}")
    Call<Operator> createOperator(@Body Operator operator, @Path("id") String id);

    //OPERATORS FOR ADMIN
    @GET("operator/list/{id}")
    Call<List<Operator>> getOperatorsForAdmin(@Path("id") String id);

    //READ ONE
    @GET("operator/{id}")
    Call<Operator> getOperator(@Path("id") String id);

}
