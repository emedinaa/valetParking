package com.example.valetparking.Administrator;

import android.os.Bundle;
import android.util.Log;

import com.example.valetparking.Database.Models.Operator;
import com.example.valetparking.Database.RetrofitClient;
import com.example.valetparking.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Operators extends AppCompatActivity {

    private String ID;
    private RecyclerView recyclerView;
    private Operators_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm__operators);

        //Conexion de la parte logica con la grafica
        recyclerView = findViewById(R.id.admin__operators_recycler_view);

        //Recycler view
        setRecyclerView();

        //Recuperar id
        ID = getIntent().getStringExtra("id");

        //Recuperar y poblar los datos
        retrieveOperators();
    }

    //Recuperar datos
    private void retrieveOperators() {
        Retrofit retrofit = RetrofitClient.getRetrofitClient();

        Call<List<Operator>> call = retrofit.create(com.example.valetparking.Database.Interfaces.Operators.class).getOperatorsForAdmin(ID);

        call.enqueue(new Callback<List<Operator>>() {
            @Override
            public void onResponse(Call<List<Operator>> call, Response<List<Operator>> response) {
                if(response.isSuccessful()){
                    List<Operator> operatorList = response.body();
                    populateOperators(operatorList);
                }
            }

            @Override
            public void onFailure(Call<List<Operator>> call, Throwable t) {
                Log.e("CONSOLE", t.getMessage());
            }
        });
    }

    //Poblar los datos
    private void populateOperators(List<Operator> operatorList) {
        List<Operators_Data> data = new ArrayList<>();

        for (Operator operator : operatorList) {
            data.add(new Operators_Data(operator.getUsername(), operator.getHourIn(), operator.getHourOut(), operator.getVehiclesIn(), operator.getVehiclesOut()));
        }
        adapter.update(data);
    }

    //Asignar recyclerView
    private void setRecyclerView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Operators_Adapter(this, new ArrayList<>());
        recyclerView.setAdapter(adapter);
    }
}