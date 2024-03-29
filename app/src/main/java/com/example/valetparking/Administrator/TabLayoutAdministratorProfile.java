package com.example.valetparking.Administrator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.valetparking.R;
import com.google.android.material.tabs.TabLayout;

public class TabLayoutAdministratorProfile extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    PagerControllerAdministratorProfile pagerAdapter;
    ImageButton imageButton;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm__profile_tab_layout);

        //Recuperar id
        id = getIntent().getStringExtra("id");

        //Conexion de la parte logica con la grafica
        tabLayout = findViewById(R.id.adm__profile_tab_layout);
        viewPager = findViewById(R.id.adm__profile_view_pager);
        imageButton = findViewById(R.id.adm__profile_image_button);

        //Creacion de los tab
        tabLayout.addTab(tabLayout.newTab().setText("Admin"));
        tabLayout.addTab(tabLayout.newTab().setText("Place"));

        //Adapter
        pagerAdapter = new PagerControllerAdministratorProfile(getSupportFragmentManager(), tabLayout.getTabCount(), id);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition() == 0){
                    pagerAdapter.notifyDataSetChanged();
                } else if(tab.getPosition() == 1){
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Button de editar
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TabLayoutAdministratorProfile.this, UpdateProfileAdministrator.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TabLayoutAdministratorProfile.this, TabLayoutAdministrator.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}