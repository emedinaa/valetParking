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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valetparking.CardView_Adapter;
import com.example.valetparking.CardView_Data;
import com.example.valetparking.Database.Interfaces.Administrators;
import com.example.valetparking.Database.Models.Administrator;
import com.example.valetparking.Database.RetrofitClient;
import com.example.valetparking.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateProfileAdministrator extends AppCompatActivity implements OnMapReadyCallback {

    //views
    private ScrollView view_admin_place;
    private RelativeLayout view_location;

    //view_admin
    private TextInputLayout admin_name;

    //view_place
    private TextInputLayout place_name, place_type, place_description, place_phone, place_facebook, place_instagram, place_twitter;
    private CountryCodePicker place_code;

    //view_location
    private GoogleMap map;
    private SearchView searchView;
    private SupportMapFragment mapFragment;
    private ImageButton gps;

    //general
    private String Admin_name, Admin_code, Admin_phone, Admin_email, Admin_user, Admin_password;
    private String Place_name, Place_type, Place_description, Place_code, Place_phone, Place_facebook, Place_instagram, Place_twitter;
    private String latitude, longitude;
    private Button update_profile_button_next, update_profile_button_update;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm__update_profile);

        //Recuperar id
        id = getIntent().getStringExtra("id");

        //conexion de la parte logica con la grafica
            //views
            view_admin_place = findViewById(R.id.update_profile_place_view);
            view_location = findViewById(R.id.update_profile_location_view);
            update_profile_button_next = findViewById(R.id.update_profile_button_next);
            update_profile_button_update = findViewById(R.id.update_profile_button_update);

            //admin
                //TextInputLayouts
                admin_name = findViewById(R.id.update_profile_admin_name);;

            //place
                //TextInputLayouts
                place_name = findViewById(R.id.update_profile_place_name);
                place_type = findViewById(R.id.update_profile_place_type);
                place_description = findViewById(R.id.update_profile_place_description);
                place_phone = findViewById(R.id.update_profile_place_telephone);
                place_facebook = findViewById(R.id.update_profile_place_facebook);
                place_instagram = findViewById(R.id.update_profile_place_instagram);
                place_twitter = findViewById(R.id.update_profile_place_twitter);

                //CountryCodePicker
                place_code = findViewById(R.id.update_profile_place_code);

        //location
        searchView = findViewById(R.id.update_profile_location_search);
        gps = findViewById(R.id.update_profile_location_gps);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.update_profile_location_map);
        mapFragment.getMapAsync(this);

        //Visualizar primera vista
        view_admin_place.setVisibility(View.VISIBLE);

        //Cambio de vista
        update_profile_button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_place_validateData(v);
            }
        });

        //Update
        update_profile_button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog().show();
                updateAdministrator(retrieveAdministrator());
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

        retrieveAndPoblateAdministrator();
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
                setFields(v);
                Intent intent = new Intent(UpdateProfileAdministrator.this, TabLayoutAdministratorProfile.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        return alertDialog;
    }

    //Metodo para setear los campos
    private void setFields(View view){
        //admin
        admin_name.getEditText().setText("");;

        admin_name.setHelperText(null);

        admin_name.clearFocus();

        //place
        place_name.getEditText().setText("");
        place_type.getEditText().setText("");
        place_description.getEditText().setText("");
        place_phone.getEditText().setText("");
        place_facebook.getEditText().setText("");
        place_instagram.getEditText().setText("");
        place_twitter.getEditText().setText("");

        place_name.setHelperText(null);
        place_type.setHelperText(null);
        place_description.setHelperText(null);
        place_phone.setHelperText(null);
        place_facebook.setHelperText(null);
        place_instagram.setHelperText(null);
        place_twitter.setHelperText(null);

        place_name.clearFocus();
        place_description.clearFocus();
        place_phone.clearFocus();
        place_facebook.clearFocus();
        place_instagram.clearFocus();
        place_twitter.clearFocus();
    }

    //ADMIN

        //Validar nombre
        private boolean profile_admin_validateName(View view, String text_name){
            Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
            if(!pattern.matcher(text_name).matches()){
                admin_name.setHelperText("Invalid name");
                return false;
            } else {
                admin_name.setHelperText(null);
            }
            return true;
        }

    //PLACE

        //Validar nombre
        private boolean profile_place_validateName(View view, String text_name){
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
        private boolean profile_place_validateType(View view, String text_type){
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
        private boolean profile_place_validateDescription(View view, String text_description){
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
        private boolean profile_place_validatePhone(View view, String text_phone){
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
        private void profile_place_validateData(View view){
            setAdmin_name(admin_name.getEditText().getText().toString());

            setPlace_name(place_name.getEditText().getText().toString());
            setPlace_type(place_type.getEditText().getText().toString());
            setPlace_description(place_description.getEditText().getText().toString());
            setPlace_code(place_code.getSelectedCountryCodeWithPlus());
            setPlace_phone(place_phone.getEditText().getText().toString());
            setPlace_facebook(place_facebook.getEditText().getText().toString());
            setPlace_instagram(place_instagram.getEditText().getText().toString());
            setPlace_twitter(place_twitter.getEditText().getText().toString());

            boolean booleanAdminName = profile_admin_validateName(view, getAdmin_name());

            boolean booleanName = profile_place_validateName(view, getPlace_name());
            boolean booleanType = profile_place_validateType(view, getPlace_type());
            boolean booleanDescription = profile_place_validateDescription(view, getPlace_description());
            boolean booleanPhone = profile_place_validatePhone(view, getPlace_phone());

            if(booleanAdminName & booleanName & booleanType & booleanDescription & booleanPhone){
                view_admin_place.setVisibility(View.INVISIBLE);
                update_profile_button_next.setVisibility(View.INVISIBLE);

                view_location.setVisibility(View.VISIBLE);
                update_profile_button_update.setVisibility(View.VISIBLE);
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

    //LOCATION

        //Metodo para el searchView
        private void location_searchMethod(){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {
                    String location = searchView.getQuery().toString();
                    List<Address> addressList = null;

                    if(location != null || !location.equals("")){
                        Geocoder geocoder = new Geocoder(UpdateProfileAdministrator.this);

                        try {
                            addressList = geocoder.getFromLocationName(location, 1);
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                        Address address = addressList.get(0);

                        //Obtenemos ubicacion
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                        //Guardar latitud y longitud
                        setLatitude(String.valueOf(address.getLatitude()));
                        setLongitude(String.valueOf(address.getLongitude()));

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

            LocationManager locationManager = (LocationManager) UpdateProfileAdministrator.this.getSystemService(Context.LOCATION_SERVICE);

            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    //Recuperamos la ubicacion actual y la guardamos
                    LatLng myUbication = new LatLng(location.getLatitude(), location.getLongitude());

                    //Guardar latitud y longitud
                    setLatitude(String.valueOf(location.getLatitude()));
                    setLongitude(String.valueOf(location.getLongitude()));

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

    //RETROFIT

        //Recuperar datos y poblarlos
        private void retrieveAndPoblateAdministrator() {
            Retrofit retrofit = RetrofitClient.getRetrofitClient();

            Call<Administrator> call = retrofit.create(Administrators.class).getAdministrator(id);

            call.enqueue(new Callback<Administrator>() {
                @Override
                public void onResponse(Call<Administrator> call, Response<Administrator> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    } else {
                        Administrator administrator = response.body();

                        admin_name.getEditText().setText(administrator.getAdminName());
                        setAdmin_code(administrator.getAdminCode());
                        setAdmin_phone(administrator.getAdminPhone());
                        setAdmin_email(administrator.getAdminEmail());
                        setAdmin_user(administrator.getAdminUsername());
                        setAdmin_password(administrator.getAdminPassword());

                        place_name.getEditText().setText(administrator.getPlaceName());
                        place_type.getEditText().setText(administrator.getPlaceType());
                        place_description.getEditText().setText(administrator.getPlaceDescription());
                        place_facebook.getEditText().setText(administrator.getPlaceFacebook());
                        place_instagram.getEditText().setText(administrator.getPlaceInstagram());
                        place_twitter.getEditText().setText(administrator.getPlaceTwitter());
                        place_code.setCountryForPhoneCode(Integer.parseInt(administrator.getPlaceCode()));
                        place_phone.getEditText().setText(administrator.getPlacePhone());

                        setLatitude(administrator.getLatitude());
                        setLongitude(administrator.getLongitude());

                    }
                }

                @Override
                public void onFailure(Call<Administrator> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        //Recuperar datos
        private Administrator retrieveAdministrator() {
            Administrator administrator = new Administrator();

            //Personal info
            administrator.setAdminName(getAdmin_name());
            administrator.setAdminCode(getAdmin_code());
            administrator.setAdminPhone(getAdmin_phone());
            administrator.setAdminEmail(getAdmin_email());
            administrator.setAdminUsername(getAdmin_user());
            administrator.setAdminPassword(getAdmin_password());

            //Place
            administrator.setPlaceName(getPlace_name());
            administrator.setPlaceType(getPlace_type());
            administrator.setPlaceDescription(getPlace_description());
            administrator.setPlaceCode(getPlace_code());
            administrator.setPlacePhone(getPlace_phone());
            administrator.setPlaceFacebook(getPlace_facebook());
            administrator.setPlaceInstagram(getPlace_instagram());
            administrator.setPlaceTwitter(getPlace_twitter());

            //Location
            administrator.setLatitude(getLatitude());
            administrator.setLongitude(getLongitude());

            return administrator;
        }

        //Guardar datos
        private  void updateAdministrator(Administrator administrator) {
            Retrofit retrofit = RetrofitClient.getRetrofitClient();

            Call<Administrator> call = retrofit.create(Administrators.class).updateAdminitrator(administrator, id);

            call.enqueue(new Callback<Administrator>() {
                @Override
                public void onResponse(Call<Administrator> call, Response<Administrator> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    } else {
                        customDialog().show();
                    }
                }

                @Override
                public void onFailure(Call<Administrator> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


    //Metodos getter y setter
    public String getAdmin_name() {
        return Admin_name;
    }

    public void setAdmin_name(String admin_name) {
        Admin_name = admin_name;
    }

    public String getAdmin_phone() {
        return Admin_phone;
    }

    public void setAdmin_phone(String admin_phone) {
        Admin_phone = admin_phone;
    }

    public String getAdmin_email() {
        return Admin_email;
    }

    public void setAdmin_email(String admin_email) {
        Admin_email = admin_email;
    }

    public String getAdmin_code() {
        return Admin_code;
    }

    public void setAdmin_code(String admin_code) {
        Admin_code = admin_code;
    }

    public String getAdmin_user() {
        return Admin_user;
    }

    public void setAdmin_user(String admin_user) {
        Admin_user = admin_user;
    }

    public String getAdmin_password() {
        return Admin_password;
    }

    public void setAdmin_password(String admin_password) {
        Admin_password = admin_password;
    }

    public String getPlace_name() {
        return Place_name;
    }

    public void setPlace_name(String place_name) {
        Place_name = place_name;
    }

    public String getPlace_type() {
        return Place_type;
    }

    public void setPlace_type(String place_type) {
        Place_type = place_type;
    }

    public String getPlace_description() {
        return Place_description;
    }

    public void setPlace_description(String place_description) {
        Place_description = place_description;
    }

    public String getPlace_code() {
        return Place_code;
    }

    public void setPlace_code(String place_code) {
        Place_code = place_code;
    }

    public String getPlace_phone() {
        return Place_phone;
    }

    public void setPlace_phone(String place_phone) {
        Place_phone = place_phone;
    }

    public String getPlace_facebook() {
        return Place_facebook;
    }

    public void setPlace_facebook(String place_facebook) {
        Place_facebook = place_facebook;
    }

    public String getPlace_instagram() {
        return Place_instagram;
    }

    public void setPlace_instagram(String place_instagram) {
        Place_instagram = place_instagram;
    }

    public String getPlace_twitter() {
        return Place_twitter;
    }

    public void setPlace_twitter(String place_twitter) {
        Place_twitter = place_twitter;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}