package com.example.valetparking.Administrator;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.valetparking.MainActivity;
import com.example.valetparking.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ProfileActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm__profile);

        //conexion de la parte logica con la grafica
        bottomNavigationView = findViewById(R.id.admin_profile_nav_bar);
        floatingActionButton = findViewById(R.id.admin_profile_floating_button);

        //floating action button
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Button navigation view
        bottomNavigationView.setBackground(null);
        //bottomNavigationView.getMenu().getItem(2).setEnabled(false);

        getSupportFragmentManager().beginTransaction().replace(R.id.admin_profile_frame,new ProfileAdmin()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp = null;

                switch (item.getItemId()){
                    case R.id.option_profile_admin:
                        temp = new ProfileAdmin();
                        break;
                    case R.id.option_profile_place:
                        temp = new ProfilePlace();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.admin_profile_frame, temp).commit();

                return true;
            }
        });
    }
}