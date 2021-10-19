package com.example.valetparking.Database.Interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Authentication {

    @FormUrlEncoded
    @POST("authentication")
    Call<ResponseBody> checkLogin(@Field("username") String username, @Field("password") String password);
}
