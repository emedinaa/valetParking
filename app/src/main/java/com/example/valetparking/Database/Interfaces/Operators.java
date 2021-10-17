package com.example.valetparking.Database.Interfaces;

import com.example.valetparking.Database.Models.Operator;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Operators {

    @GET("operator")
    Call<List<Operator>> getOperators();
}
