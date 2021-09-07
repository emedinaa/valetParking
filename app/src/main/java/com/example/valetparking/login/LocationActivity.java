package com.example.valetparking.login;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.valetparking.MainActivity;
import com.example.valetparking.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    SearchView searchView;
    SupportMapFragment mapFragment;
    ImageButton gps;
    Button location_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log__location);

        //Conexion de parte logica con grafica
        searchView = findViewById(R.id.location_search);
        gps = findViewById(R.id.location_gps);
        location_button = findViewById(R.id.location_button);

        //Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.location_google_map);
        mapFragment.getMapAsync(this);

        //Metodo para el GPS
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realLocation();
            }
        });

        //Metodo para el location_button
        location_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog().show();
            }
        });
    }

    //Metodo del alertDialog (Mensaje emergente)
    private AlertDialog customDialog(){
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Obtener el layout inflater
        LayoutInflater inflater = getLayoutInflater();

        //Inflar y establecer el layout para el dialogo
        //Pasar nulo como vista principal porque va en el diseno del dialogo
        View view =  inflater.inflate(R.layout.gen__alert_dialog, null);

        //Dimensiones del alertDialog
        view.setMinimumWidth((int)(getResources().getDisplayMetrics().widthPixels * 0.40));
        view.setMinimumHeight((int)(getResources().getDisplayMetrics().heightPixels * 0.40));

        //Conexion de la parte logica con grafica
        TextView alert_title = view.findViewById(R.id.alert_title);
        TextView alert_message = view.findViewById(R.id.alert_message);
        ImageView alert_image = view.findViewById(R.id.alert_image);
        Button alert_button = view.findViewById(R.id.alert_button);

        //Cambio de tamaño de letra
        alert_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        alert_message.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        //Personalizacion del alertDialog
        alert_title.setText(getResources().getString(R.string.alert_title_register));
        alert_message.setText(getResources().getString(R.string.alert_message_register));
        alert_image.setBackground(getResources().getDrawable(R.drawable.alert__register));

        //Mostrar alertDialog
        builder.setView(view);
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Cerrar alertDialog
        alert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent = new Intent(LocationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        return alertDialog;
    }

    //Metodo para el searchView
    private void searchMethod(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if(location != null || !location.equals("")){
                    Geocoder geocoder = new Geocoder(LocationActivity.this);

                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);

                    //Obtenemos ubicacion
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                    //Colocar marcador
                    map.addMarker(new MarkerOptions().position(latLng).title(location));

                    //Posicionamos la camara
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(14).bearing(90).tilt(45).build();
                    map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mapFragment.getMapAsync(this);
    }

    //Metodo para ubicacion en tiempo real
    public void realLocation(){
        int permises = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if(permises == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }

        LocationManager locationManager = (LocationManager) LocationActivity.this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //Recuperamos la ubicacion actual y la guardamos
                LatLng myUbication = new LatLng(location.getLatitude(), location.getLongitude());

                //Colocamos marcador en la ubicacion actual y movemos la camara
                CameraPosition cameraPosition = new CameraPosition.Builder().target(myUbication).zoom(14).bearing(90).tilt(45).build();
                map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}

            @Override
            public void onProviderEnabled(@NonNull String provider) {}

            @Override
            public void onProviderDisabled(@NonNull String provider) {}
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,0, locationListener);
    }

    //OnMapReady
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        //Permisos de ubicacion en tiempo real
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        //Accedemos a la ubicacion en tiempo real
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);

        searchMethod();
    }
}