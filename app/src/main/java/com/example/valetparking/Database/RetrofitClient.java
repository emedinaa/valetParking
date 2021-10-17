package com.example.valetparking.Database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String BASE_URL = "http://localhost:4000/"; //http://192.168.8.16:4000/
    private static Retrofit retrofit;
    private static Gson gson;

    public static Retrofit getRetrofitClient(){

        gson = new GsonBuilder()
                .serializeNulls()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}