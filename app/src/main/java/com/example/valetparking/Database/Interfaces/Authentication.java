package com.example.valetparking.Database.Interfaces;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Authentication {

    @POST("authentication")
    Call<String> checkLogin(@Header("auth-token") String authToken);
}
