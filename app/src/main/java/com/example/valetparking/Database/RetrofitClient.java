package com.example.valetparking.Database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String BASE_URL = "http://192.168.8.16:4000/"; //http://192.168.8.16:4000/
    private static Retrofit retrofit;
    private static Gson gson;

    public static Retrofit getRetrofitClient(){

        //Creamos un interceptor y le indicamos el log level a usar
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //Asociamos el interceptor a las peticiones
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);

        gson = new GsonBuilder()
                .serializeNulls()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();

        return retrofit;
    }
}