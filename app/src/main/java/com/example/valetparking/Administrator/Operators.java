package com.example.valetparking.Administrator;

import android.os.Bundle;

import com.example.valetparking.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    private void setRecyclerView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Operators_Adapter(this, getList());
        recyclerView.setAdapter(adapter);
    }

    private List<Operators_Data> getList(){
        List<Operators_Data> data = new ArrayList<>();

        data.add(new Operators_Data("Joel",700,1200,8,8));
        data.add(new Operators_Data("Gabriel",700,1200,12,12));
        data.add(new Operators_Data("Jesus",1200,1600,14,14));
        data.add(new Operators_Data("Maria",1200,1600,15,15));
        data.add(new Operators_Data("Sofia",1200,1600,18,18));
        data.add(new Operators_Data("Mariangel",1600,2300,18,18));
        data.add(new Operators_Data("Ariana",1600,2300,18,18));
        data.add(new Operators_Data("Pablo",1600,2300 ,18,18));

        return data;
    }
}