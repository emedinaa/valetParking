package com.example.valetparking.Database.Interfaces;

import com.example.valetparking.Database.Models.Operator;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Operators {

    //CREATE OPERATOR
    @POST("operator/create/{id}")
    Call<Operator> createOperator(@Body Operator operator, @Path("id") String id);

    //READ OPERATOR
    @GET("operator/{id}")
    Call<Operator> getOperator(@Path("id") String id);

    //OPERATORS FOR ADMIN
    @GET("operator/administrator/list/{id}")
    Call<List<Operator>> getOperatorsForAdmin(@Path("id") String id);

    //OPERATORS FOR OPERATOR
    @GET("operator/list/{id}")
    Call<List<Operator>> getOperatorsForOperator(@Path("id") String id);

    //DELETE FOR USERNAME
    @DELETE("operator/delete/{username}")
    Call<ResponseBody> deleteOperatorForUsername(@Path("username") String username);

    //UPDATE HOUR IN
    @PUT("operator/hourIn/{id}")
    Call<Operator> updateHourIn(@Path("id") String id);

    //UPDATE HOUR OUT
    @PUT("operator/hourOut/{id}")
    Call<Operator> updateHourOut(@Path("id") String id);

}
