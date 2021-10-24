package com.example.valetparking.Database.Interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Authentication {

    //LOGIN
    @FormUrlEncoded
    @POST("authentication/login")
    Call<ResponseBody> checkLogin(@Field("username") String username, @Field("password") String password);

    //VERIFICAR USUARIO
    @FormUrlEncoded
    @POST("authentication/verify")
    Call<ResponseBody> verifyUsername(@Field("username") String username);

    //ENVIAR SMS CON EL TOKEN
    @POST("sms/forgot/{id}")
    Call<ResponseBody> tokenSMS(@Path("id") String id);

    //ENVIAR EMAIL CON EL TOKEN
    @POST("email/forgot/{id}")
    Call<ResponseBody> tokenEmail(@Path("id") String id);

    //CAMBIAR CLAVE
    @FormUrlEncoded
    @PUT("authentication/change/{id}")
    Call<ResponseBody> changePassword(@Path("id") String id, @Field("password") String password);
}
