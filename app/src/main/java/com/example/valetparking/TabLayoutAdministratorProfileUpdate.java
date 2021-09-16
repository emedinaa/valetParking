package com.example.valetparking;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.valetparking.Administrator.PagerControllerAdministratorProfileUpdate;
import com.example.valetparking.Administrator.ProfileUpdateAdmin;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class TabLayoutAdministratorProfileUpdate extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ImageButton imageButton;
    PagerControllerAdministratorProfileUpdate pagerAdapter;

    ProfileUpdateAdmin profileUpdateAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm__profile_update_tab_layout);

        //Conexion de la parte logica con la grafica
        tabLayout = findViewById(R.id.adm__profile_update_tab_layout);
        viewPager = findViewById(R.id.adm__profile_update_view_pager);
        imageButton = findViewById(R.id.adm__profile_update_image_button);

        //Referencia de las otras vistas
        profileUpdateAdmin = new ProfileUpdateAdmin();

        //Creacion de los tab
        tabLayout.addTab(tabLayout.newTab().setText("Admin"));
        tabLayout.addTab(tabLayout.newTab().setText("Place"));
        tabLayout.addTab(tabLayout.newTab().setText("Location"));

        //Adapter
        pagerAdapter = new PagerControllerAdministratorProfileUpdate(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition() == 0){
                    pagerAdapter.notifyDataSetChanged();
                } else if(tab.getPosition() == 1){
                    pagerAdapter.notifyDataSetChanged();
                } else if(tab.getPosition() == 2){
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validateFields();
            }
        });
    }

     public void validateFields(){
        if(profileUpdateAdmin.getFullname_edit().getText().toString().equals("")){
            Toast.makeText(this, "Vacio", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Lleno", Toast.LENGTH_SHORT).show();
        }

    }
}