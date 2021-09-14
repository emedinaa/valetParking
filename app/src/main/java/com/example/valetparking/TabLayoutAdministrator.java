package com.example.valetparking;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.valetparking.Administrator.OperatorsActivity;
import com.example.valetparking.Administrator.PagerControllerAdministrator;
import com.example.valetparking.Administrator.SettingsActivity;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class TabLayoutAdministrator extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    PagerControllerAdministrator pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gen__tab_layout);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        //Creacion de los tab
        tabLayout.addTab(tabLayout.newTab().setText("Tickets"));
        tabLayout.addTab(tabLayout.newTab().setText("Cancel vehicle"));
        tabLayout.addTab(tabLayout.newTab().setText("Operator account"));

        //Adapter
        pagerAdapter = new PagerControllerAdministrator(getSupportFragmentManager(), tabLayout.getTabCount());
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

    //option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.adm__option_menu, menu);
        return true;
    }

    //option menu item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;

        switch (item.getItemId()){
            case R.id.option_profile:
                intent = new Intent(TabLayoutAdministrator.this, TabLayoutAdministratorProfileUpdate.class);
                startActivity(intent);
                break;
            case R.id.option_operators:
                intent = new Intent(TabLayoutAdministrator.this, OperatorsActivity.class);
                startActivity(intent);
                break;
            case R.id.option_logout:
                intent = new Intent(TabLayoutAdministrator.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.option_settings:
                intent = new Intent(TabLayoutAdministrator.this, SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}