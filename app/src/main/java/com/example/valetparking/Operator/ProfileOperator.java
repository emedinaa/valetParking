package com.example.valetparking.Operator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.valetparking.R;

public class ProfileOperator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.op__profile);

        Button button_profile_back = findViewById(R.id.operator_profile_button);

        button_profile_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileOperator.this, TabLayoutOperator.class);
                startActivity(intent);
            }
        });
    }
}