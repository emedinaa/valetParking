package com.example.valetparking.Operator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.valetparking.Database.Interfaces.Operators;
import com.example.valetparking.Database.Models.Operator;
import com.example.valetparking.Database.RetrofitClient;
import com.example.valetparking.MainActivity;
import com.example.valetparking.R;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TabLayoutOperator extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    PagerControllerOperator pagerAdapter;
    String id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gen__tab_layout);

        //Recuperar id
        id = getIntent().getStringExtra("id");

        //Conexion de la parte logica con la grafica
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


    //Anular el button back
    @Override
    public void onBackPressed() { }

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
                intent.putExtra("id", id);
                startActivity(intent);
                break;
            case R.id.option_close_tickets:
                intent = new Intent(TabLayoutOperator.this, CloseTicket.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
            case R.id.option_logout:
                updateHourIn();
                intent = new Intent(TabLayoutOperator.this, MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Update hour in
    private void updateHourIn() {

        Retrofit retrofit = RetrofitClient.getRetrofitClient();

        Call<Operator> call = retrofit.create(Operators.class).updateHourOut(id);

        call.enqueue(new Callback<Operator>() {
            @Override
            public void onResponse(Call<Operator> call, Response<Operator> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                } else {

                }
            }

            @Override
            public void onFailure(Call<Operator> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}