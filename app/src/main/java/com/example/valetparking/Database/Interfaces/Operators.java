package com.example.valetparking.Database.Interfaces;

import com.example.valetparking.Database.Models.Operator;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Operators {

    //READ ALL
    @GET("operator")
    Call<List<Operator>> getOperators();

    //READ ONE
    @GET("operator/{id}")
    Call<Operator> getOperator(@Path("id") String id);
}
