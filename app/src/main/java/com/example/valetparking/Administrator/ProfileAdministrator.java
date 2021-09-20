package com.example.valetparking.Administrator;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valetparking.CardView_Adapter;
import com.example.valetparking.CardView_Data;
import com.example.valetparking.R;
import com.example.valetparking.login.CreateAccount;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ProfileAdministrator extends AppCompatActivity implements OnMapReadyCallback {

    //views
    ScrollView profile_view_admin, profile_view_place;
    RelativeLayout profile_view_location;

    //view_admin
    TextInputLayout profile_admin_name, profile_admin_phone, profile_admin_email, profile_admin_user, profile_admin_password, profile_admin_confirm_password;
    TextInputEditText profile_admin_Name, profile_admin_Phone, profile_admin_Email, profile_admin_User, profile_admin_Password, profile_admin_Confirm_password;
    CountryCodePicker profile_admin_code;

    //view_place
    TextInputLayout profile_place_name, profile_place_type, profile_place_description, profile_place_phone, profile_place_facebook, profile_place_instagram, profile_place_twitter;
    TextInputEditText profile_place_Name, profile_place_Description, profile_place_Phone, profile_place_Facebook, profile_place_Instagram, profile_place_Twitter;
    AutoCompleteTextView profile_place_Type;
    CountryCodePicker profile_place_code;

    //view_location
    GoogleMap map;
    SearchView searchView;
    SupportMapFragment mapFragment;
    ImageButton gps;

    //gen
    Button profile_button;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm__profile);

        //conexion de la parte logica con la grafica
            //views
            profile_view_admin = findViewById(R.id.profile_admin_view);
            profile_view_place = findViewById(R.id.profile_place_view);
            profile_view_location = findViewById(R.id.profile_location_view);
            profile_button = findViewById(R.id.profile_button);

            //admin
                //TextInputLayouts
                profile_admin_name = findViewById(R.id.profile_admin_name);
                profile_admin_phone = findViewById(R.id.profile_admin_telephone);
                profile_admin_email = findViewById(R.id.profile_admin_email);
                profile_admin_user = findViewById(R.id.profile_admin_username);
                profile_admin_password = findViewById(R.id.profile_admin_password);
                profile_admin_confirm_password = findViewById(R.id.profile_admin_confirm_password);

                //TextInputEditText
                profile_admin_Name = findViewById(R.id.profile_admin_name_edit);
                profile_admin_Phone = findViewById(R.id.profile_admin_telephone_edit);
                profile_admin_Email = findViewById(R.id.profile_admin_email_edit);
                profile_admin_User = findViewById(R.id.profile_admin_username_edit);
                profile_admin_Password = findViewById(R.id.profile_admin_password_edit);
                profile_admin_Confirm_password = findViewById(R.id.profile_admin_confirm_password_edit);

                //CountryCodePicker
                profile_admin_code = findViewById(R.id.profile_admin_code);

            //place
                //TextInputLayouts
                profile_place_name = findViewById(R.id.profile_place_name);
                profile_place_type = findViewById(R.id.profile_place_type);
                profile_place_description = findViewById(R.id.profile_place_description);
                profile_place_phone = findViewById(R.id.profile_place_telephone);
                profile_place_facebook = findViewById(R.id.profile_place_facebook);
                profile_place_instagram = findViewById(R.id.profile_place_instagram);
                profile_place_twitter = findViewById(R.id.profile_place_twitter);

                //TextinputEditText
                profile_place_Name = findViewById(R.id.profile_place_name_edit);
                profile_place_Description = findViewById(R.id.profile_place_description_edit);
                profile_place_Phone = findViewById(R.id.profile_place_telephone_edit);
                profile_place_Facebook = findViewById(R.id.profile_place_facebook_edit);
                profile_place_Instagram = findViewById(R.id.profile_place_instagram_edit);
                profile_place_Twitter = findViewById(R.id.profile_place_twitter_edit);

                //autoCompleteTextView
                profile_place_Type = findViewById(R.id.profile_place_type_edit);

                //CountryCodePicker
                profile_place_code = findViewById(R.id.profile_place_code);

        //location
        searchView = findViewById(R.id.profile_location_search);
        gps = findViewById(R.id.profile_location_gps);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.profile_location_map);
        mapFragment.getMapAsync(this);

        //Visualizar primera vista
        profile_view_admin.setVisibility(View.VISIBLE);

        //Cambio de vista
        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count == 0){
                    profile_admin_validateData(v);
                } else if(count == 1){
                    profile_place_validateData(v);
                } else if(count == 2 ){
                    customDialog().show();
                }
            }
        });

        //EndIcon
        profile_place_type.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place_selector().show();
            }
        });

        //Metodo para el gps en tiempo real
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location_realLocation();
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

        //Personalizacion del alertDialog
        alert_title.setText(getResources().getString(R.string.alert_title_update));
        alert_message.setText(getResources().getString(R.string.alert_message_update));
        alert_image.setBackground(getResources().getDrawable(R.drawable.icon__update));

        //Mostrar alertDialog
        builder.setView(view);
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        //Cerrar alertDialog
        alert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

                Intent intent = new Intent(ProfileAdministrator.this, TabLayoutAdministratorProfile.class);
                startActivity(intent);

                setFields(v);
            }
        });

        return alertDialog;
    }

    //Metodo para setear los campos
    private void setFields(View view){
        //admin
        profile_admin_Name.setText("");
        profile_admin_Phone.setText("");
        profile_admin_Email.setText("");
        profile_admin_User.setText("");
        profile_admin_Password.setText("");
        profile_admin_Confirm_password.setText("");

        profile_admin_name.setHelperText(null);
        profile_admin_phone.setHelperText(null);
        profile_admin_email.setHelperText(null);
        profile_admin_user.setHelperText(null);
        profile_admin_password.setHelperText(null);
        profile_admin_confirm_password.setHelperText(null);
        profile_admin_email.setHelperText(null);

        profile_admin_name.clearFocus();
        profile_admin_phone.clearFocus();
        profile_admin_email.clearFocus();
        profile_admin_user.clearFocus();
        profile_admin_password.clearFocus();
        profile_admin_confirm_password.clearFocus();

        //place
        profile_place_Name.setText("");
        profile_place_Type.setText("");
        profile_place_Description.setText("");
        profile_place_Phone.setText("");

        profile_place_name.setHelperText(null);
        profile_place_type.setHelperText(null);
        profile_place_description.setHelperText(null);
        profile_place_phone.setHelperText(null);

        profile_place_name.clearFocus();
        profile_place_description.clearFocus();
        profile_place_phone.clearFocus();
        profile_place_facebook.clearFocus();
        profile_place_instagram.clearFocus();
        profile_place_twitter.clearFocus();
    }

    //ADMIN

        //Validar nombre
        private boolean profile_admin_validateName(View view, String text_name){
            Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
            if(!pattern.matcher(text_name).matches()){
                profile_admin_name.setHelperText("Invalid name");
                return false;
            } else {
                profile_admin_name.setHelperText(null);
            }
            return true;
        }

        //Validar telefono
        private boolean profile_admin_validatePhone(View view, String text_phone){
            Pattern pattern = Pattern.compile("^[0-9]+$");
            if(!pattern.matcher(text_phone).matches()){
                profile_admin_phone.setHelperText("Invalid phone");
                return false;
            } else {
                profile_admin_phone.setHelperText(null);
            }
            return true;
        }

        //validar email
        private boolean profile_admin_validateEmail(View view, String text_email){
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]*@[a-zA-Z0-9]*.+$");
            if(!pattern.matcher(text_email).matches()){
                profile_admin_email.setHelperText("Invalid email");
                return false;
            } else {
                profile_admin_email.setHelperText(null);
            }
            return true;
        }

        //Validar usuario
        private boolean profile_admin_validateUser(View view, String text_user){
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
            if(!pattern.matcher(text_user).matches()){
                profile_admin_user.setHelperText("Invalid user");
                return false;
            } else {
                profile_admin_user.setHelperText(null);
            }
            return true;
        }

        //Validar clave
        private boolean profile_admin_validatePassword(View view, String text_password){
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
            if(!pattern.matcher(text_password).matches()){
                profile_admin_password.setHelperText("Invalid password");
                return false;
            } else {
                profile_admin_password.setHelperText(null);
            }
            return true;
        }

        //Validar confirmacion de clave
        private boolean profile_admin_validateConfirmPassword(View view, String text_confirm_password){
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
            if(!pattern.matcher(text_confirm_password).matches()){
                profile_admin_confirm_password.setHelperText("Invalid password");
                return false;
            } else {
                profile_admin_confirm_password.setHelperText(null);
            }
            return true;
        }

        //Validar datos
        private void profile_admin_validateData(View view){
            String Name = profile_admin_name.getEditText().getText().toString();
            String Phone = profile_admin_phone.getEditText().getText().toString();
            String Email = profile_admin_email.getEditText().getText().toString();
            String User = profile_admin_user.getEditText().getText().toString();
            String Password = profile_admin_password.getEditText().getText().toString();
            String Confirm_password = profile_admin_confirm_password.getEditText().getText().toString();

            boolean booleanName = profile_admin_validateName(view, Name);
            boolean booleanPhone = profile_admin_validatePhone(view,Phone);
            boolean booleanEmail = profile_admin_validateEmail(view,Email);
            boolean booleanUser = profile_admin_validateUser(view, User);
            boolean booleanPassword = profile_admin_validatePassword(view, Password);
            boolean booleanConfirmPassword = profile_admin_validateConfirmPassword(view, Confirm_password);

            if(booleanName & booleanPhone & booleanEmail & booleanUser & booleanPassword & booleanConfirmPassword){
                profile_view_admin.setVisibility(View.INVISIBLE);
                profile_view_place.setVisibility(View.VISIBLE);
                profile_view_location.setVisibility(View.INVISIBLE);

                count++;
            }
        }

    //ADMIN

    //PLACE

        //Validar nombre
        private boolean profile_place_validateName(View view, String text_name){
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]+$");
            if(!pattern.matcher(text_name).matches()){
                profile_place_name.setHelperText("Invalid name");
                return false;
            } else {
                profile_place_name.setHelperText(null);
            }
            return true;
        }

        //Validar tipo
        private boolean profile_place_validateType(View view, String text_type){
            Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
            if(!pattern.matcher(text_type).matches()){
                profile_place_type.setHelperText("Invalid type");
                return false;
            } else {
                profile_place_type.setHelperText(null);
            }
            return true;
        }

        //Validar descripcion
        private boolean profile_place_validateDescription(View view, String text_description){
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]+$");
            if(!pattern.matcher(text_description).matches()){
                profile_place_description.setHelperText("Invalid description");
                return false;
            } else {
                profile_place_description.setHelperText(null);
            }
            return true;
        }

        //Validar telefono
        private boolean profile_place_validatePhone(View view, String text_phone){
            Pattern pattern = Pattern.compile("^[0-9]+$");
            if(!pattern.matcher(text_phone).matches()){
                profile_place_phone.setHelperText("Invalid phone");
                return false;
            } else {
                profile_place_phone.setHelperText(null);
            }
            return true;
        }

        //Validar datos
        private void profile_place_validateData(View view){
            String Name = profile_place_name.getEditText().getText().toString();
            String Type = profile_place_type.getEditText().getText().toString();
            String Description = profile_place_description.getEditText().getText().toString();
            String Phone = profile_place_phone.getEditText().getText().toString();

            boolean booleanName = profile_place_validateName(view, Name);
            boolean booleanType = profile_place_validateType(view, Type);
            boolean booleanDescription = profile_place_validateDescription(view, Description);
            boolean booleanPhone = profile_place_validatePhone(view,Phone);

            if(booleanName & booleanType & booleanDescription & booleanPhone){
                profile_view_admin.setVisibility(View.INVISIBLE);
                profile_view_place.setVisibility(View.INVISIBLE);
                profile_view_location.setVisibility(View.VISIBLE);
                profile_button.setText(R.string.button_register);

                count++;
            }
        }

        //Metodo de selector de lugar
        private AlertDialog place_selector(){
            RecyclerView card_recycler_view;
            CardView_Adapter adapter;

            AlertDialog alertDialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            //Obtener el layout inflater
            LayoutInflater inflater = getLayoutInflater();

            //Inflar y establecer el layout para el dialogo
            //Pasar nulo como vista principal porque va en el diseno del dialogo
            View view = inflater.inflate(R.layout.gen__card_view_recycler_view, null);

            //Conexion de la parte logica con la grafica
            card_recycler_view = view.findViewById(R.id.card_recycler_view);

            //Dimensiones del alertDialog
            view.setMinimumWidth((int)(getResources().getDisplayMetrics().widthPixels * 0.80));
            view.setMinimumHeight((int)(getResources().getDisplayMetrics().heightPixels * 0.80));

            //RecyclerView - Adapter
            card_recycler_view.setHasFixedSize(true);
            card_recycler_view.setLayoutManager(new LinearLayoutManager(this));

            //Llenar data
            List<CardView_Data> data = new ArrayList<>();

            String[] places = getResources().getStringArray(R.array.array_places);
            int[] Places = {R.drawable.icon__place_hospital, R.drawable.icon__place_mall, R.drawable.icon__place_restaurant};

            for (int i = 0; i < places.length; i++) {
                data.add(new CardView_Data(places[i], Places[i]));
            }

            //Asignar adapter
            adapter = new CardView_Adapter(this, data);
            card_recycler_view.setAdapter(adapter);

            //Mostrar alertDialog
            builder.setView(view);
            builder.setCancelable(true);
            alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            //Asignar valor seleccionado y cerrar alertDialog
            adapter.setOnClickLister(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    profile_place_type.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                    alertDialog.dismiss();
                }
            });

            return alertDialog;
        }

    //PLACE

    //LOCATION

    //Metodo para el searchView
    private void location_searchMethod(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if(location != null || !location.equals("")){
                    Geocoder geocoder = new Geocoder(ProfileAdministrator.this);

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
    public void location_realLocation(){
        int permises = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if(permises == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }

        LocationManager locationManager = (LocationManager) ProfileAdministrator.this.getSystemService(Context.LOCATION_SERVICE);

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

        location_searchMethod();
    }

    //LOCATION
}