package com.example.valetparking;

import android.os.Bundle;

import com.example.valetparking.Administrator.PagerControllerAdministratorProfileUpdate;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class TabLayoutAdministratorProfileUpdate extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    PagerControllerAdministratorProfileUpdate pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm__profile_update_tab_layout);

        //Conexion de la parte logica con la grafica
        tabLayout = findViewById(R.id.adm__profile_update_tab_layout);
        viewPager = findViewById(R.id.adm__profile_update_view_pager);

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
    }
}