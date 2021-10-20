package com.example.valetparking.Operator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.valetparking.MainActivity;
import com.example.valetparking.R;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class TabLayoutOperator extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    PagerControllerOperator pagerAdapter;
    String id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gen__tab_layout);

        id = getIntent().getStringExtra("id");

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        //Creacion de los tab
        tabLayout.addTab(tabLayout.newTab().setText("Check in"));
        tabLayout.addTab(tabLayout.newTab().setText("Open ticket"));
        tabLayout.addTab(tabLayout.newTab().setText("Check out"));

        //Adapter
        pagerAdapter = new PagerControllerOperator(getSupportFragmentManager(), tabLayout.getTabCount(), id);
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
        getMenuInflater().inflate(R.menu.op__option_menu, menu);
        return true;
    }

    //option menu item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;

        switch (item.getItemId()){
            case R.id.option_profile:
                intent = new Intent(TabLayoutOperator.this, ProfileOperator.class);
                startActivity(intent);
                break;
            case R.id.option_close_tickets:
                intent = new Intent(TabLayoutOperator.this, CloseTicket.class);
                startActivity(intent);
                break;
            case R.id.option_logout:
                intent = new Intent(TabLayoutOperator.this, MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}