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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.valetparking.CardView_Adapter;
import com.example.valetparking.CardView_Data;
import com.example.valetparking.MainActivity;
import com.example.valetparking.R;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CreateAccount extends AppCompatActivity implements OnMapReadyCallback {

    //viewa
    ScrollView view_admin, view_place;
    RelativeLayout view_location;

    //view_admin
    TextInputLayout admin_name, admin_phone, admin_email, admin_user, admin_password, admin_confirm_password;
    TextInputEditText admin_Name, admin_Phone, admin_Email, admin_User, admin_Password, admin_Confirm_password;
    CountryCodePicker admin_code;

    //view_place
    TextInputLayout place_name, place_type, place_description, place_phone, place_facebook, place_instagram, place_twitter;
    TextInputEditText place_Name, place_Description, place_Phone, place_Facebook, place_Instagram, place_Twitter;
    AutoCompleteTextView place_Type;
    CountryCodePicker place_code;

    //view_location
    GoogleMap map;
    SearchView searchView;
    SupportMapFragment mapFragment;
    ImageButton gps;

    //gen
    Button create_account_button;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log__create_account_);

        //Conexion de la parte logica con la grafica
            //views
            view_admin = findViewById(R.id.create_account_admin_view);
            view_place = findViewById(R.id.create_account_place_view);
            view_location = findViewById(R.id.create_account_location_view);
            create_account_button = findViewById(R.id.create_account_button);

            //admin
                //TextInputLayouts
                admin_name = findViewById(R.id.create_account_admin_name);
                admin_phone = findViewById(R.id.create_account_admin_telephone);
                admin_email = findViewById(R.id.create_account_admin_email);
                admin_user = findViewById(R.id.create_account_admin_username);
                admin_password = findViewById(R.id.create_account_admin_password);
                admin_confirm_password = findViewById(R.id.create_account_admin_confirm_password);

                //TextInputEditText
                admin_Name = findViewById(R.id.create_account_name_edit);
                admin_Phone = findViewById(R.id.create_account_telephone_edit);
                admin_Email = findViewById(R.id.create_account_email_edit);
                admin_User = findViewById(R.id.create_account_username_edit);
                admin_Password = findViewById(R.id.create_account_password_edit);
                admin_Confirm_password = findViewById(R.id.create_account_confirm_password_edit);

                //CountryCodePicker
                admin_code = findViewById(R.id.create_account_admin_code);

            //place
                //TextInputLayouts
                place_name = findViewById(R.id.create_account_place_name);
                place_type = findViewById(R.id.create_account_place_type);
                place_description = findViewById(R.id.create_account_place_description);
                place_phone = findViewById(R.id.create_account_place_telephone);
                place_facebook = findViewById(R.id.create_account_place_facebook);
                place_instagram = findViewById(R.id.create_account_place_instagram);
                place_twitter = findViewById(R.id.create_account_place_twitter);

                //TextinputEditText
                place_Name = findViewById(R.id.create_account_place_name_edit);
                place_Description = findViewById(R.id.create_account_place_description_edit);
                place_Phone = findViewById(R.id.create_account_place_telephone_edit);
                place_Facebook = findViewById(R.id.create_account_place_facebook_edit);
                place_Instagram = findViewById(R.id.create_account_place_instagram_edit);
                place_Twitter = findViewById(R.id.create_account_place_twitter_edit);

                //autoCompleteTextView
                place_Type = findViewById(R.id.create_account_place_type_edit);

                //CountryCodePicker
                place_code = findViewById(R.id.create_account_place_code);

            //location
            searchView = findViewById(R.id.create_account_location_search);
            gps = findViewById(R.id.create_account_location_gps);
            mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.create_account_location_map);
            mapFragment.getMapAsync(this);

        //Visualizar primera vista
        view_admin.setVisibility(View.VISIBLE);

        //Cambio de vista
        create_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count == 0){
                    admin_validateData(v);
                } else if(count == 1){
                    place_validateData(v);
                } else if(count == 2 ){
                    customDialog().show();
                }
            }
        });

        //EndIcon
        place_type.setEndIconOnClickListener(new View.OnClickListener() {
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
                Intent intent = new Intent(CreateAccount.this, MainActivity.class);
                startActivity(intent);

                setFields(v);
            }
        });

        return alertDialog;
    }

    //Metodo para setear los campos
    private void setFields(View view){
        //admin
        admin_Name.setText("");
        admin_Phone.setText("");
        admin_Email.setText("");
        admin_User.setText("");
        admin_Password.setText("");
        admin_Confirm_password.setText("");

        admin_name.setHelperText(null);
        admin_phone.setHelperText(null);
        admin_email.setHelperText(null);
        admin_user.setHelperText(null);
        admin_password.setHelperText(null);
        admin_confirm_password.setHelperText(null);
        admin_email.setHelperText(null);

        admin_name.clearFocus();
        admin_phone.clearFocus();
        admin_email.clearFocus();
        admin_user.clearFocus();
        admin_password.clearFocus();
        admin_confirm_password.clearFocus();

        //place
        place_Name.setText("");
        place_Type.setText("");
        place_Description.setText("");
        place_Phone.setText("");

        place_name.setHelperText(null);
        place_type.setHelperText(null);
        place_description.setHelperText(null);
        place_phone.setHelperText(null);

        place_name.clearFocus();
        place_description.clearFocus();
        place_phone.clearFocus();
        place_facebook.clearFocus();
        place_instagram.clearFocus();
        place_twitter.clearFocus();
    }

    //ADMIN

        //Validar nombre
        private boolean admin_validateName(View view, String text_name){
            Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
            if(!pattern.matcher(text_name).matches()){
                admin_name.setHelperText("Invalid name");
                return false;
            } else {
                admin_name.setHelperText(null);
            }
            return true;
        }

        //Validar telefono
        private boolean admin_validatePhone(View view, String text_phone){
            Pattern pattern = Pattern.compile("^[0-9]+$");
            if(!pattern.matcher(text_phone).matches()){
                admin_phone.setHelperText("Invalid phone");
                return false;
            } else {
                admin_phone.setHelperText(null);
            }
            return true;
        }

        //validar email
        private boolean admin_validateEmail(View view, String text_email){
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]*@[a-zA-Z0-9]*.+$");
            if(!pattern.matcher(text_email).matches()){
                admin_email.setHelperText("Invalid email");
                return false;
            } else {
                admin_email.setHelperText(null);
            }
            return true;
        }

        //Validar usuario
        private boolean admin_validateUser(View view, String text_user){
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
            if(!pattern.matcher(text_user).matches()){
                admin_user.setHelperText("Invalid user");
                return false;
            } else {
                admin_user.setHelperText(null);
            }
            return true;
        }

        //Validar clave
        private boolean admin_validatePassword(View view, String text_password){
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
            if(!pattern.matcher(text_password).matches()){
                admin_password.setHelperText("Invalid password");
                return false;
            } else {
                admin_password.setHelperText(null);
            }
            return true;
        }

        //Validar confirmacion de clave
        private boolean admin_validateConfirmPassword(View view, String text_confirm_password){
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
            if(!pattern.matcher(text_confirm_password).matches()){
                admin_confirm_password.setHelperText("Invalid password");
                return false;
            } else {
                admin_confirm_password.setHelperText(null);
            }
            return true;
        }

        //Validar datos
        private void admin_validateData(View view){
            String Name = admin_name.getEditText().getText().toString();
            String Phone = admin_phone.getEditText().getText().toString();
            String Email = admin_email.getEditText().getText().toString();
            String User = admin_user.getEditText().getText().toString();
            String Password = admin_password.getEditText().getText().toString();
            String Confirm_password = admin_confirm_password.getEditText().getText().toString();

            boolean booleanName = admin_validateName(view, Name);
            boolean booleanPhone = admin_validatePhone(view,Phone);
            boolean booleanEmail = admin_validateEmail(view,Email);
            boolean booleanUser = admin_validateUser(view, User);
            boolean booleanPassword = admin_validatePassword(view, Password);
            boolean booleanConfirmPassword = admin_validateConfirmPassword(view, Confirm_password);

            if(booleanName & booleanPhone & booleanEmail & booleanUser & booleanPassword & booleanConfirmPassword){
                view_admin.setVisibility(View.INVISIBLE);
                view_place.setVisibility(View.VISIBLE);
                view_location.setVisibility(View.INVISIBLE);

                count++;
            }
        }

    //ADMIN

    //PLACE

        //Validar nombre
        private boolean place_validateName(View view, String text_name){
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]+$");
            if(!pattern.matcher(text_name).matches()){
                place_name.setHelperText("Invalid name");
                return false;
            } else {
                place_name.setHelperText(null);
            }
            return true;
        }

        //Validar tipo
        private boolean place_validateType(View view, String text_type){
            Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
            if(!pattern.matcher(text_type).matches()){
                place_type.setHelperText("Invalid type");
                return false;
            } else {
                place_type.setHelperText(null);
            }
            return true;
        }

        //Validar descripcion
        private boolean place_validateDescription(View view, String text_description){
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]+$");
            if(!pattern.matcher(text_description).matches()){
                place_description.setHelperText("Invalid description");
                return false;
            } else {
                place_description.setHelperText(null);
            }
            return true;
        }

        //Validar telefono
        private boolean place_validatePhone(View view, String text_phone){
            Pattern pattern = Pattern.compile("^[0-9]+$");
            if(!pattern.matcher(text_phone).matches()){
                place_phone.setHelperText("Invalid phone");
                return false;
            } else {
                place_phone.setHelperText(null);
            }
            return true;
        }

        //Validar datos
        private void place_validateData(View view){
            String Name = place_name.getEditText().getText().toString();
            String Type = place_type.getEditText().getText().toString();
            String Description = place_description.getEditText().getText().toString();
            String Phone = place_phone.getEditText().getText().toString();

            boolean booleanName = place_validateName(view, Name);
            boolean booleanType = place_validateType(view, Type);
            boolean booleanDescription = place_validateDescription(view, Description);
            boolean booleanPhone = place_validatePhone(view,Phone);

            if(booleanName & booleanType & booleanDescription & booleanPhone){
                view_admin.setVisibility(View.INVISIBLE);
                view_place.setVisibility(View.INVISIBLE);
                view_location.setVisibility(View.VISIBLE);
                create_account_button.setText(R.string.button_register);

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
                    place_type.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
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
                        Geocoder geocoder = new Geocoder(CreateAccount.this);

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

            LocationManager locationManager = (LocationManager) CreateAccount.this.getSystemService(Context.LOCATION_SERVICE);

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