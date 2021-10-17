package com.example.valetparking.Administrator;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valetparking.Database.Models.Operator;
import com.example.valetparking.Database.RetrofitClient;
import com.example.valetparking.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Operators extends AppCompatActivity {

    RecyclerView recyclerView;
    Operators_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm__operators);

        //Conexion de la parte logica con la grafica
        recyclerView = findViewById(R.id.admin__operators_recycler_view);

        setRecyclerView();
    }

    //Asignar recyclerView
    private void setRecyclerView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Operators_Adapter(this, getList());
        recyclerView.setAdapter(adapter);
    }

    //Retrofit
    Retrofit retrofit = RetrofitClient.getRetrofitClient();

    //Metodo para llenar los datos
    private List<Operators_Data> getList(){
        List<Operators_Data> data = new ArrayList<>();

        //ROUTE
        com.example.valetparking.Database.Interfaces.Operators operators = retrofit.create(com.example.valetparking.Database.Interfaces.Operators.class);

        //MODEL
        Call<List<Operator>> call = operators.getOperators();

        //CALLBACK
        call.enqueue(new Callback<List<Operator>>() {
            @Override
            public void onResponse(Call<List<Operator>> call, Response<List<Operator>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    List<Operator> operatorList = response.body();

                    for(Operator operator : operatorList){
                        data.add(new Operators_Data(operator.getName(), operator.getHourIn(), operator.getHourOut(), operator.getVehiclesIn(), operator.getVehiclesOut()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Operator>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        data.add(new Operators_Data("Mari044",1739,1800,88,6));
        data.add(new Operators_Data("JoelG28",1739,1800,88,6));

        return data;
    }
}