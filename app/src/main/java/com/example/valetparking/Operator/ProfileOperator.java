package com.example.valetparking.Operator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.valetparking.Database.Interfaces.Operators;
import com.example.valetparking.Database.Models.Operator;
import com.example.valetparking.Database.RetrofitClient;
import com.example.valetparking.R;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileOperator extends AppCompatActivity {

    private String id;
    private TextInputLayout username, name, phone, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.op__profile);

        //Conexion de la parte logica con la grafica
            //TextInputLayout
            username = findViewById(R.id.operator_profile_username);
            name = findViewById(R.id.operator_profile_name);
            phone = findViewById(R.id.operator_profile_telephone);
            email = findViewById(R.id.operator_profile_email);

            //Button
            Button button_profile_back = findViewById(R.id.operator_profile_button);

        //Recuperar id
        id = getIntent().getStringExtra("id");

        //Button back
        button_profile_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileOperator.this, TabLayoutOperator.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        //Recuperar datos
        retrieveOperator();
    }

    //Anular el button back
    @Override
    public void onBackPressed() { }

    //Recuperar datos y poblarlos
    private void retrieveOperator() {
        Retrofit retrofit = RetrofitClient.getRetrofitClient();

        Call<Operator> call = retrofit.create(Operators.class).getOperator(id);

        call.enqueue(new Callback<Operator>() {
            @Override
            public void onResponse(Call<Operator> call, Response<Operator> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    Operator operator = response.body();

                    username.getEditText().setText(operator.getUsername());
                    name.getEditText().setText(operator.getName());
                    phone.getEditText().setText(operator.getCode() + operator.getPhone());
                    email.getEditText().setText(operator.getEmail());

                }
            }

            @Override
            public void onFailure(Call<Operator> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}